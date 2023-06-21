const logoutBtn = document.getElementById("logoutBtn");
const searchContent = document.getElementById("searchContent");
const searchBtn = document.getElementById("searchBtn");
const categoryBtns = document.getElementsByClassName("category-item");
const inputcontent = document.getElementsByClassName("inputcontent");
const tr = document.querySelector(".inputcontent > tr");
const writeBtn = document.getElementById("writepageBtn");
const mypageBtn = document.getElementById("mypageBtn");

// 로그아웃 버튼 클릭하면 첫 화면으로 이동
async function handleLogout(){
    await fetch(`/login`)
    .then(res => res.json())
    .then(res => {
        console.log(res);
        alert(`${res[0].id}님 로그아웃 되었습니다.`);
        return location.href="/";
    });
}

// 게시글 출력하는 공통함수
function boardWrite(res){
    // 기존의 tr안의 td 모두 삭제
    const remove_tr = inputcontent[0].getElementsByTagName('tr');
    const tr_length = remove_tr.length;
    
    for (var i = 0; i < tr_length; i++) {
        remove_tr[0].remove();  // 위에부터 차례로 삭제하므로 0으로 지정
    }

    for (let i = 0; i < res.length; i++) {
        let tr = document.createElement("tr");
        let td = document.createElement("td");
        td.textContent = `${i+1}`;
        tr.appendChild(td);
        td = document.createElement("td");
        td.textContent = `${res[i].category_id == '2' ? '한식' : 
        res[i].category_id == '3' ? '일식' : 
        res[i].category_id == '4' ? '양식' : 
        res[i].category_id == '5' ? '중식' :
        res[i].category_id == '6' ? '카페' : 
        res[i].category_id == '7' ? '패스트푸드' :
        res[i].category_id}`;
        tr.appendChild(td);
        td = document.createElement("td");
        td.textContent = `${res[i].title}`;
        tr.appendChild(td);
        td = document.createElement("td");
        td.textContent = `${res[i].end_time}`;
        tr.appendChild(td);
        td = document.createElement("td");
        td.textContent = `${res[i].location}`;
        tr.appendChild(td);
        td = document.createElement("td");
        td.textContent = `${res[i].content_state}`;
        tr.appendChild(td);
        inputcontent[0].appendChild(tr);
    };
}

// 카테고리 별 버튼 클릭하면 해당 내용만 추출
async function handlerCategory(event){
    console.log("카테고리 버튼 클릭!!!");
    let categoryData = {
        method:'POST',
        body:JSON.stringify({"category_id": event.target.id, "category_name": event.target.name}),
        headers:{
            'Content-Type':'application/json',
        }
    }
    console.log(categoryData);
    await fetch(`/category`, categoryData)
    .then(res => res.json())
    .then(res => {
    boardWrite(res);
    })
}

// 검색 버튼 클릭하면 검색어에 해당하는 카테고리 id가져오기
async function handleSearch(){
    console.log("검색시작!!!!!!");
    let searchData = {
        method:'POST',
        body:JSON.stringify({"searchData":searchContent.value}),
        headers:{
            'Content-Type':'application/json',
        }
    }
    console.log(searchData);
    await fetch(`/search`, searchData)
    .then(res => res.json())
    .then(res => {
        console.log(res);
    })
}

// 게시글 버튼 클릭하면 게시글 작성 페이지로 이동
function handleWrite(){
    return location.href = "/post/createform";
}

async function handleMypage(){
    await fetch(`/login`)
    .then(res => res.json())
    .then(res => {
        return location.href=`/mypage/${res[0].id}`;
    });
}

logoutBtn.addEventListener("click", handleLogout);
searchBtn.addEventListener("click", handleSearch);
writeBtn.addEventListener("click", handleWrite);
mypageBtn.addEventListener("click", handleMypage);

for (let i=0; i < categoryBtns.length; i++){
    categoryBtns[i].addEventListener("click", handlerCategory);
}