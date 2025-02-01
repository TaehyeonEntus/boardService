package com.taehyeon.boardService.service;

import com.taehyeon.boardService.dto.*;
import com.taehyeon.boardService.entity.KakaoMember;
import com.taehyeon.boardService.entity.LocalMember;
import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.exception.AuthException.AuthException;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import com.taehyeon.boardService.repository.KakaoMemberRepository;
import com.taehyeon.boardService.repository.LocalMemberRepository;
import com.taehyeon.boardService.repository.MemberRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final LocalMemberRepository localMemberRepository;
    private final MemberService memberService;
    private final KakaoMemberRepository kakaoMemberRepository;
    @Value("${kakao.client_id}")
    private String clientId;
    private String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    public String getAccessTokenFromKakao(String code) {
        System.out.println("code = " + code);
        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();


        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Id Token ------> {}", kakaoTokenResponseDto.getIdToken());

        return kakaoTokenResponseDto.getAccessToken();
    }

    public KakaoUserInfoResponseDto getKakaoUserInfo(String accessToken) {
        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // access token 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                //TODO : Custom Exception
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        log.info("[ Kakao Service ] Auth ID ---> {} ", userInfo.getId());
        log.info("[ Kakao Service ] NickName ---> {} ", userInfo.getKakaoAccount().getProfile().getNickName());
        log.info("[ Kakao Service ] ProfileImageUrl ---> {} ", userInfo.getKakaoAccount().getProfile().getProfileImageUrl());

        return userInfo;
    }

    public void registerKakaoMember(KakaoMemberRegisterRequestDto kakaoMemberRegisterRequestDto) {
        KakaoMember kakaoMember = kakaoMemberRepository.findByKakaoMemberId(kakaoMemberRegisterRequestDto.getKakaoMemberId());
        Member memberByNickname = memberRepository.findByNickname(kakaoMemberRegisterRequestDto.getNickname());
        if (kakaoMember != null)
            throw new MemberException("이미 가입하신 아이디 입니다.");
        if (memberByNickname != null)
            throw new MemberException("이미 사용중인 닉네임 입니다.");
        memberService.addKakaoMember(new KakaoMember(
                kakaoMemberRegisterRequestDto.getKakaoMemberId(),
                kakaoMemberRegisterRequestDto.getNickname()));
    }

    public void registerLocalMember(LocalMemberRegisterRequestDto localMemberRegisterRequestDto) throws MemberException {
        LocalMember localMember = localMemberRepository
                .findByUsername(localMemberRegisterRequestDto.getUsername());
        Member memberByNickname = memberRepository
                .findByNickname(localMemberRegisterRequestDto.getNickname());
        //아이디 검사
        if (localMember != null)
            throw new MemberException("이미 가입하신 아이디 입니다.");
        //비밀번호 검사
        if (!localMemberRegisterRequestDto.getPassword().equals(localMemberRegisterRequestDto.getPasswordConfirm()))
            throw new MemberException("비밀번호가 일치하지 않습니다.");
        //닉네임 검사
        if (memberByNickname != null)
            throw new MemberException("이미 사용중인 닉네임 입니다.");
        memberService.addLocalMember(new LocalMember(
                localMemberRegisterRequestDto.getUsername(),
                localMemberRegisterRequestDto.getNickname(),
                localMemberRegisterRequestDto.getPassword()));
    }

    public Long loginLocalMember(LocalMemberLoginDto localMemberLoginDto) {
        String username = localMemberLoginDto.getUsername();
        String password = localMemberLoginDto.getPassword();

        LocalMember localMember = localMemberRepository.findByUsername(username);
        if(localMember == null){
            //아이디 오류
            throw new MemberException("존재하지 않는 아이디");
        }
        else if(!localMember.getPassword().equals(password)){
            throw new AuthException("잘못된 비밀번호");
        }
        return localMember.getId();
    }
}
