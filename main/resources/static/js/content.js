// 클릭한 테이블의 목록의 카테고리 id를 반환한다.
// $("#inputcontent").click(function(){   
//     var tr = $(this);
//     var td = tr.children();
//     categoryid = td.eq(1).text();
//     console.log(categoryid);
//     loginUserInfo(categoryid);
// })

// login 사용자 정보 요청 함수
async function loginUserInfo(categoryid){
    console.log("로그인 사용자 정보 내놔");
    await fetch(`/login`)
    .then(res => res.json())
    .then(res => {
        console.log(res[0].id);
        console.log(categoryid);
        var data = new Array();
        data.userid = res[0].id;
        data.categoryid = categoryid;
        console.log(data);
        contenthandler(data);
    })
}

// 반환받은 사용자 id, contentid를 이용해서 content.html에 전달
async function contenthandler(data){
    console.log('내용 불러온다 딱대');
    let contentData = {
        method:'POST',
        body: JSON.stringify({"userid":data.userid, "contentid":data.categoryid}),
        headers:{
            'Content-Type': 'application/json',
        }
    };
    console.log(contentData);
    await fetch(`/post/ids`, contentData)
    .then(res => res.json())
    .then(res => {
        console.log(res);
    })
    return location.href="/content";
}


