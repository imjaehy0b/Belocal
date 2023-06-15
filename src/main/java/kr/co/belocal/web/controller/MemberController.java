package kr.co.belocal.web.controller;


import jakarta.servlet.http.HttpSession;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.service.MemberServiceImpl;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;


    //아이디 중복 확인
    @PostMapping("sign-up/checkId")
    @ResponseBody
    public int CheckId(@RequestParam("id") String id){

        System.out.println("id = " + id);
        if (id.equals(memberService.checkId(id)))
            return 1;
        return 0;
    }

    //닉네임 중복 확인
    @PostMapping("sign-up/checkNickName")
    @ResponseBody
    public int CheckNickName(@RequestParam("nickName") String nickName){

        if (nickName.equals(memberService.checkNickName(nickName)))
            return 1;
        return 0;
    }

//    @PostMapping("sign-up/checkPhoneNum")
//    public boolean CheckPhoneNum(@RequestParam("phoneNum") String phoneNum){
//        if (phoneNum.equals(memberService.CheckPhoneNum(phoneNum)))
//            return true;
//        return false;
//    }


    //로그인페이지
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    //로그인 정보 전송
    @PostMapping("login")
    public String  login(Member member , HttpSession session){

        //로그인 실패
        if (!memberService.login(member))
            return "redirect:/login?error";

        //로그인에 성공한다면


        return "redirect:/";
    }

    //회원가입 페이지
    @GetMapping("/sign-up")
    public String join(){
        return "sign-up";
    }

    //회원가입 정보 전송
    @PostMapping("/sign-up")
    public String join(Member member,HttpSession session){
        memberService.save(member);
        return "login/login";
    }

    //아이디 찾기
    @GetMapping("/login/find-id")
    public String findId(){
        return "login/find-id";
    }

    //비밀번호 찾기
    @GetMapping("/login/find-pw")
    public String findPw(){
        return "login/find-pw";
    }

}
