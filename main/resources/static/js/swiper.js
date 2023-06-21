window.onload = function(){
    swiperfunc();    
}
function swiperfunc(){
    const swiper = new Swiper('#swiper', {
        direction: 'horizontal',
        effect:'fade',
        loop: false,
    
        autoplay:{
            display:2500,
            disableOnInteraction:false,
        },

        pagination: {
            el: '.swiper-pagination',
        },
    });
}
