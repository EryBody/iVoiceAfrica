(function ($) {
  'use strict';

  /*================================
    Preloader
    ==================================*/

  const preloader = $('#preloader');
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
    const scroll = $(window).scrollTop(),
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
      const forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      const validation = Array.prototype.filter.call(forms, function (form) {
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
  // $('ul#nav_menu').slicknav({
  //   prependTo: '#mobile_menu',
  // });

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
    const owl = $('.testimonial-carousel').owlCarousel({
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
    const requestFullscreen = function (ele) {
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

    const exitFullscreen = function () {
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

    const fsDocButton = document.getElementById('full-view');
    const fsExitDocButton = document.getElementById('full-view-exit');

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

const menuBtn = document.querySelector('.menu-icon');
const overlay = document.querySelector('.overlay');
const page = document.querySelector('.page-container');

if (menuBtn) {
  menuBtn.addEventListener('click', function (e) {
    page.classList.add('nav-open');
  });
}
if (overlay) {
  overlay.addEventListener('click', () => {
    page.classList.remove('nav-open');
  });
}

// * Profile Modal

const profileButton = document.querySelector('.profile');
const profileModal = document.querySelector('.profile__modal');
profileButton.addEventListener('click', function () {
  this.classList.toggle('profile-active');
  // profileModal.style.zIndex = 99;
});

// main.
const main = document.querySelector('.main');
const sidebar = document.querySelector('.sidebar');
const header = document.querySelector('.header');

function removeModal(item) {
  item.addEventListener('click', () => {
    const view = document.querySelector('.view-option');
    // if (view.classList.contains('display')) view.classList.remove('display');
    if (profileButton.classList.contains('profile-active')) {
      profileButton.classList.remove('profile-active');
    }
  });
}

removeModal(main);
removeModal(sidebar);

const inprogressBtn = document.querySelector('.inprogress-select');
if (inprogressBtn) {
  inprogressBtn.addEventListener('click', () => {
    document.querySelector('.inprogress-modal').classList.toggle('hidden');
  });
}

// ACTIVEPAGE
const collapseMenu = document.querySelector('.collapse');
const collapse = document.querySelector('.collapse');
const activeLink = document.querySelector('.active-link');
document.querySelectorAll('.nav-link').forEach((link) => {
  const containsCollapse = link.classList.contains('collapse-link');

  if (link.href === window.location.href.replace('?', '')) {
    link.setAttribute('aria-current', 'page');
    // console.log(link.href);
    if (containsCollapse) {
      document.querySelector('.active-link').addEventListener('click', () => {
        // collapse.classList.toggle('in');
        collapse.style.height = 0;
        collapse.classList.remove('in');
      });
      collapse.classList.add('in');
    }
  }
});

const descTab = document.getElementById('descTab');
const deliTab = document.getElementById('deliTab');
const attachTab = document.getElementById('attTab');
// const jobListBtn = document.querySelector('.job-list-details-active');

const descContent = document.getElementById('descriptionContent');
const deliContent = document.getElementById('deliverablesContent');
const attContent = document.getElementById('attachmentContent');

// TOGGLING COMPONENTS
if (descTab) {
  descTab.addEventListener('click', function () {
    this.classList.add('job-list-details-active');
    descContent.style.display = 'block';

    deliContent.style.display = 'none';
    deliTab.classList.remove('job-list-details-active');

    attTab.classList.remove('job-list-details-active');
    attContent.style.display = 'none';
  });
}
if (deliTab) {
  deliTab.addEventListener('click', function () {
    console.log('hi');
    this.classList.add('job-list-details-active');
    deliContent.style.display = 'block';

    descTab.classList.remove('job-list-details-active');
    descContent.style.display = 'none';

    attachTab.classList.remove('job-list-details-active');
    attContent.style.display = 'none';
  });
}
if (attachTab) {
  attachTab.addEventListener('click', function () {
    this.classList.add('job-list-details-active');
    attContent.style.display = 'block';

    descTab.classList.remove('job-list-details-active');
    descContent.style.display = 'none';

    deliTab.classList.remove('job-list-details-active');
    deliContent.style.display = 'none';
  });
}



const hired = document.querySelector('.hired');
const hireBtn = document.querySelector('.hire-btn');
if (hireBtn) {
  hireBtn.addEventListener('click', function () {
    this.classList.toggle('hired');
    this.classList.toggle('clientrequesteditBtn');
    if (this.classList.contains('hired')) {
      this.textContent = 'hired';
    } else {
      this.textContent = 'hire';
    }
  });
}
const ddText = document.querySelectorAll('.dropdown-text');
const ddInput = document.querySelector('.dd-input');

ddText.forEach((item) =>
  item.addEventListener('click', function () {
    ddInput.value = this.textContent;
  })
);
let dropDown = document.querySelector('.inprogress-dropdown');
if (dropDown) {
  dropDown.addEventListener('click', () => {
    dropDown.classList.toggle('hidden');
  });
}

function imgUrl(obj, id, btn) {
  document.getElementById(id).click();
  if (obj === null) return;
  const file = obj.value;
  console.log(file);
  const fileName = file.split('\\');
  document.querySelector(btn).innerHTML = fileName[fileName.length - 1];
}

// OPTIONS MODALS AND TWEAKS//
const viewBtn = document.getElementById('view-option');
const optionsBtn = document.querySelector('.options-btn');
const recordingBtn = document.getElementById('record');
const recording = document.getElementById('recording-option');
const playBtn = document.querySelector('.audio-btns');
const audio = document.getElementById('myAudio');
const slider = document.getElementById('audioSlider');

// if (optionsBtn) {
//   optionsBtn.addEventListener('click', () => {
//     document.querySelector('.files-options').classList.toggle('appear');
//   });
// }
// if (viewBtn) {
//   viewBtn.addEventListener('click', function () {
//     this.classList.toggle('active');
//     recording.classList.remove('show');
//     audio.pause();
//     audio.currentTime = 0;
//   });
// }
// if (recordingBtn) {
//   recordingBtn.addEventListener('click', function () {
//     recording.classList.toggle('show');
//     viewBtn.classList.remove('active');
//   });
// }
if (playBtn) {
  playBtn.addEventListener('click', function () {
    this.classList.toggle('active');
    if (audio.paused) {
      audio.play();
    } else {
      audio.pause();
    }

    audio.addEventListener('timeupdate', function () {
      slider.value = (audio.currentTime / audio.duration) * 100;
    });

    // When the range slider value is changed, update the audio current time
    slider.addEventListener('input', function () {
      audio.currentTime = (slider.value / 100) * audio.duration;
    });
  });
}
