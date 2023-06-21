const id = document.getElementById("id");
const pwd1 = document.getElementById("pswd1");
const pwd2 = document.getElementById("pswd2");
const nickname = document.getElementById("nickname");

const idBtn = document.getElementById("idBtn");
const pwBtn = document.getElementById("pwBtn");
const nickBtn = document.getElementById("nickBtn");
const backBtn = document.getElementById("backBtn");
const joinResultBtn = document.getElementById("joinResultBtn");

// ID POST, GET하여 ID 사용여부 결정하는 함수
async function handleCheckId(e) {
    e.preventDefault(); //기본동작 막음
    let idData = {
        method:'POST',
        body: JSON.stringify({"id":id.value}),
        headers:{
            'Content-Type': 'application/json',
        }
    };
    await fetch(`/login/id/new`, idData)
    .then(res => res.json())
    .then(res => {
        if (res){
            alert("사용 가능한 아이디입니다.");
        } else{
            alert("이미 존재한 아이디입니다.");
        }  
    })
    return "redirect:/login/new";
}

// PW GET, POST하여 비교한 후 일치여부 판단
async function handleCheckPw(e){
    if(pwd1.value.length != '0' && pwd2.value.length != '0'){
        e.preventDefault(); 
        let pwData = {
            method:'POST',
            body: JSON.stringify({"pwd1":pwd1.value, "pwd2":pwd2.value}),
            headers:{
                'Content-Type': 'application/json',
            }
        };
        await fetch(`/login/pw/new`, pwData)
        .then(res => res.json())
        .then(res => {
            if (res){
                alert("비밀번호가 일치합니다.");
            } else{
                alert("비밀번호가 일치하지않습니다.");
            }
        })
    }
    return "redirect:/login/new";
}

// 닉네임 중복, 생성 여부 판단
async function handleCheckNick(e) {
    e.preventDefault(); 
    let nickData = {
        method:'POST',
        body: JSON.stringify({"nickname":nickname.value}),
        headers:{
            'Content-Type': 'application/json',
        }
    };
    await fetch(`/login/nick/new`, nickData)
    .then(res => res.json())
    .then(res => {
        if (res){
            alert("사용 가능한 닉네임입니다.");
        } else{
            alert("이미 존재한 닉네임입니다.");
        }  
    })
    return "redirect:/login/new";
}

function handleBack(){
    location.href="/";
}

async function handleLogin(e) {
    e.preventDefault(); 
    await fetch(`/login/check/new`)
    .then(res => res.json())
    .then(res => {
        if (res){
            alert(`${nickname.value}님 환영합니다!`);
            return location.href="/";
        } else{
            alert(`회원정보가 일치하지 않습니다.`);
            return location.href="/login/new";
        }  
    })
}

// 회원가입 기능
idBtn.addEventListener("click", handleCheckId);
pwBtn.addEventListener("click", handleCheckPw);
nickBtn.addEventListener("click", handleCheckNick);
backBtn.addEventListener("click", handleBack);
joinResultBtn.addEventListener("click", handleLogin);