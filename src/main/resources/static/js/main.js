$(function(){



	//main menu event
	$('.nav_btn').mouseover(function(){
		$('.main_menu').stop().slideDown();
		$(this).addClass('on');
	});

	$('nav').mouseleave(function(){
		$('.main_menu').stop().slideUp();
		$('.nav_btn').removeClass('on');
	});


	$('.main_menu > li').hover(function(){
		$('.main_menu > li').find('.sub_menu').stop().fadeOut(500);
		$(this).find('.sub_menu').stop().fadeIn();
	}, function(){
		$('.main_menu > li').find('.sub_menu').stop().fadeOut(500);
	});





    //main mobile sch box
    $('.mo_sch_btn').click(function(){
        $('.mo_search_box').stop().slideToggle();
    });




    var main_swiper = new Swiper('.swiper-container', {
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        loop: true,
        speed : 700,
        slidesPerView: 1,
        grabCursor: true,
        autoplay: {
            delay: 5000,
            disableOnInteraction: false,
        },
    });



    //admin page menu click slide
    $('.admin_menu > li > a').click(function(e){
        e.preventDefault();
    });
    $('.admin_menu > li').click(function(){
        $(this).find('.admin_sub').toggleClass('slide_on');
        $(this).siblings().find('.admin_sub').removeClass('slide_on');
    });




    //admin page 회원 그룹별 tab
    $('.group_tab > li').click(function(){
        var i = $(this).index();
        $('.group_tab > li').removeClass('active');
        $(this).addClass('active');
        $('.group > div').removeClass('on');
        $('.group > div').eq(i).addClass('on');
    });




    //admin page 상품등록 tab
    $('.group_tab2 > li:first').click(function(){
        $('.group_tab2_sub').stop().slideUp();
        $('.group_tab2_sub > li').removeClass('on');
        $('.group_content2_1 > div').removeClass('on');
        $('.group > .group_content1').removeClass('on');
        $('.group > .group_content1').addClass('on');
    });

    $('.group_tab2 > li > button').click(function(){
        $('.group_tab2_sub').stop().slideToggle();
    });

    $('.group_tab2_sub > li').click(function(){
        var i2 = $(this).index();
        $('.group_content1').removeClass('on');
        $('.group_content2_1 > div').removeClass('on');
        $('.group_content2_1 > div').eq(i2).addClass('on');
        $('.group_tab2_sub').stop().slideUp();
		$('.group_tab2 > li > button').text($(this).text());
		//$('.group_tab2 > li > button').addClass('active');
    });





});