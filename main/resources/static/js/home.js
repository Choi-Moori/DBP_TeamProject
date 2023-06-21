const loginId = document.getElementById("InputId");
const loginPw = document.getElementById("InputPassword");
const loginBtn = document.getElementById("loginBtn");

async function handleCheckLogin(e) {
    if(InputId.value.length != 0 & loginPw.value.length != 0){
        e.preventDefault();  
        let loginData = {
            method:'POST',
            body: JSON.stringify({"inputID":loginId.value, "inputPW":loginPw.value}),
            headers:{
                'Content-Type': 'application/json',
            }
        };
        await fetch(`/login`, loginData)
        .then(res => res.json())
        .then(res => {
            console.log(res[0].login == 'false');
            if (res[0].login=='true'){
                return location.href="/login/main";
            } else{
                alert(`아이디, 비밀번호를 확인해주세요.`);
            }  
        })
    }
}

// 로그인 기능
loginBtn.addEventListener("click", handleCheckLogin);