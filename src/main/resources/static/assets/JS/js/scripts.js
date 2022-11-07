(function ($) {
  'use strict';

  /*================================
    Preloader
    ==================================*/

  var preloader = $('#preloader');
  $(window).on('load', function () {
    setTimeout(function () {
      preloader.fadeOut('slow', function () {
        $(this).remove();
      });
    }, 300);
  });

  /*================================
    sidebar menu
    ==================================*/
  $('#menu').metisMenu();

  /*================================
    slimscroll activation
    ==================================*/
  $('.menu-inner').slimScroll({
    height: 'auto',
  });
  $('.nofity-list').slimScroll({
    height: '435px',
  });
  $('.timeline-area').slimScroll({
    height: '500px',
  });
  $('.recent-activity').slimScroll({
    height: 'calc(100vh - 114px)',
  });
  $('.settings-list').slimScroll({
    height: 'calc(100vh - 158px)',
  });

  /*================================
    stickey Header
    ==================================*/
  $(window).on('scroll', function () {
    var scroll = $(window).scrollTop(),
      mainHeader = $('#sticky-header'),
      mainHeaderHeight = mainHeader.innerHeight();

    // console.log(mainHeader.innerHeight());
    if (scroll > 1) {
      $('#sticky-header').addClass('sticky-menu');
    } else {
      $('#sticky-header').removeClass('sticky-menu');
    }
  });

  /*================================
    form bootstrap validation
    ==================================*/
  $('[data-toggle="popover"]').popover();

  /*------------- Start form Validation -------------*/
  window.addEventListener(
    'load',
    function () {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function (form) {
        form.addEventListener(
          'submit',
          function (event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add('was-validated');
          },
          false
        );
      });
    },
    false
  );

  /*================================
    datatable active
    ==================================*/
  if ($('#dataTable').length) {
    $('#dataTable').DataTable({
      responsive: true,
    });
  }
  if ($('#dataTable2').length) {
    $('#dataTable2').DataTable({
      responsive: true,
    });
  }
  if ($('#dataTable3').length) {
    $('#dataTable3').DataTable({
      responsive: true,
    });
  }

  /*================================
    Slicknav mobile menu
    ==================================*/
  $('ul#nav_menu').slicknav({
    prependTo: '#mobile_menu',
  });

  /*================================
    login form
    ==================================*/
  $('.form-gp input').on('focus', function () {
    $(this).parent('.form-gp').addClass('focused');
  });
  $('.form-gp input').on('focusout', function () {
    if ($(this).val().length === 0) {
      $(this).parent('.form-gp').removeClass('focused');
    }
  });

  /*================================
    slider-area background setting
    ==================================*/
  $('.settings-btn, .offset-close').on('click', function () {
    $('.offset-area').toggleClass('show_hide');
    $('.settings-btn').toggleClass('active');
  });

  /*================================
    Owl Carousel
    ==================================*/
  function slider_area() {
    var owl = $('.testimonial-carousel').owlCarousel({
      margin: 50,
      loop: true,
      autoplay: false,
      nav: false,
      dots: true,
      responsive: {
        0: {
          items: 1,
        },
        450: {
          items: 1,
        },
        768: {
          items: 2,
        },
        1000: {
          items: 2,
        },
        1360: {
          items: 1,
        },
        1600: {
          items: 2,
        },
      },
    });
  }
  slider_area();

  /*================================
    Fullscreen Page
    ==================================*/

  if ($('#full-view').length) {
    var requestFullscreen = function (ele) {
      if (ele.requestFullscreen) {
        ele.requestFullscreen();
      } else if (ele.webkitRequestFullscreen) {
        ele.webkitRequestFullscreen();
      } else if (ele.mozRequestFullScreen) {
        ele.mozRequestFullScreen();
      } else if (ele.msRequestFullscreen) {
        ele.msRequestFullscreen();
      } else {
        console.log('Fullscreen API is not supported.');
      }
    };

    var exitFullscreen = function () {
      if (document.exitFullscreen) {
        document.exitFullscreen();
      } else if (document.webkitExitFullscreen) {
        document.webkitExitFullscreen();
      } else if (document.mozCancelFullScreen) {
        document.mozCancelFullScreen();
      } else if (document.msExitFullscreen) {
        document.msExitFullscreen();
      } else {
        console.log('Fullscreen API is not supported.');
      }
    };

    var fsDocButton = document.getElementById('full-view');
    var fsExitDocButton = document.getElementById('full-view-exit');

    fsDocButton.addEventListener('click', function (e) {
      e.preventDefault();
      requestFullscreen(document.documentElement);
      $('body').addClass('expanded');
    });

    fsExitDocButton.addEventListener('click', function (e) {
      e.preventDefault();
      exitFullscreen();
      $('body').removeClass('expanded');
    });
  }
})(jQuery);

// * Profile Modal

// const proBtn = document.getElementById('profileModal');
// const showModalBtn = document.getElementById('show_modal_btn');
// const profileModalActive = document.querySelector('.profile-Modal_active');

// showModalBtn.addEventListener('click', (e) => {
//   proBtn.classList.toggle('profile-modal_active');
// });

// * Client New Request Multisection from

const steps = [...document.querySelectorAll('div .step')];
const nextBtn = document.querySelectorAll('.next-btn');
const prevBtn = document.querySelectorAll('prev-btn');
const clientRequestForm = document.querySelector('.client-newrequest');

if (clientRequestForm) {
  clientRequestForm.addEventListener('click', (e) => {
    e.preventDefault();
  });
}

nextBtn.forEach((button) => {
  button.addEventListener('click', (e) => {
    changeStep('next');
    clientRequestForm.preventDefault();
  });
});

prevBtn.forEach((button) => {
  button.addEventListener('click', (e) => {
    changeStep('prev');
  });
});

function changeStep(btn) {
  let index = 0;
  const active = document.querySelector('.step.active');
  index = steps.indexOf(active);
  steps[index].classList.remove('active');
  if (btn === 'next') {
    index++;
  } else if (btn === 'prev') {
    index--;
  }
  steps[index].classList.add('active');
}

// * For the Sidbar to side in and out

const hamburgerMenu = document.querySelector('.menu-icon');
const overlay = document.querySelector('.overlay');
const page = document.querySelector('.page-container');
const mainPageContent = document.querySelector('.main-pagecontent');
const biggerMarginDiv = [...document.querySelectorAll('#bigger-margin')];

if (hamburgerMenu) {
  hamburgerMenu.addEventListener('click', (e) => {
    page.classList.add('nav-open');

    console.log('hi');
  });
}
if (overlay) {
  overlay.addEventListener('click', () => {
    page.classList.remove('nav-open');
  });
}
function alternatePage() {
  sidebar.classList.toggle('sidebar-menu-active');
  mainPageContent.classList.toggle('main-pagecontent-active');
  biggerMarginDiv.forEach((div) => {
    div.classList.toggle('ml-7');
  });
}

// * Description Carousel

// const descTab = document.getElementById('descTab');
// const devTab = document.getElementById('devTab');
// const attTab = document.getElementById('attTab');
// const jobListBtn = document.querySelector('.job-list-details-active');

// const descContent = document.getElementById('descriptionContent');
// const devContent = document.getElementById('delivarablesContent');
// const attContent = document.getElementById('attachmentContent');

// descTab.onclick = () => {
//   descContent.style.display = 'block';
//   devContent.style.display = 'none';
//   attContent.style.display = 'none';
// };
// devTab.onclick = () => {
//   descContent.style.display = 'none';
//   devContent.style.display = 'block';
//   attContent.style.display = 'none';
// };
// attTab.onclick = () => {
//   descContent.style.display = 'none';
//   devContent.style.display = 'none';
//   attContent.style.display = 'block';
// };
