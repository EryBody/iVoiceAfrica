const freelanceBox = document.getElementById('Freelance-box');
const clientBox = document.getElementById('client-box');
const clientIcon = document.querySelector('#client-icon');
const btn = document.querySelector('.lol');
const signModal = document.querySelector('.sign-modal');
const freelancerIcon = document.querySelector('#Freelancer-icon');

// FREELANCER BOX
if (btn)
  btn.addEventListener('click', () => (signModal.style.display = 'none'));
if (freelanceBox) {
  freelanceBox.addEventListener('mouseover', () =>
    freelancerIcon.classList.add('Freelancer-icon')
  );
  freelanceBox.addEventListener('mouseleave', () =>
    freelancerIcon.classList.remove('Freelancer-icon')
  );
  freelanceBox.addEventListener('click', (e) => {
    document.getElementById('btn-normal').style.display = 'none';
    freelancerIcon.classList.add('Freelancer-icon');
    clientIcon.classList.remove('client-icon');
    freelanceBox.style.border = '1px solid #151617';
    clientBox.style.border = 'none';
    document.getElementById('btn-freelancer').style.display = 'inline-block';
    document.getElementById('btn-client').style.display = 'none';
  });
}

// CLIENT BOX
if (clientBox) {
  clientBox.addEventListener('mouseover', () =>
    clientIcon.classList.add('client-icon')
  );
  clientBox.addEventListener('mouseleave', () =>
    clientIcon.classList.remove('client-icon')
  );
  clientBox.addEventListener('click', (e) => {
    clientIcon.classList.add('client-icon');
    clientBox.style.border = '1px solid #151617';
    freelancerIcon.classList.remove('Freelancer-icon');
    freelanceBox.style.border = 'none';
    document.getElementById('btn-normal').style.display = 'none';
    document.getElementById('btn-freelancer').style.display = 'none';
    document.getElementById('btn-client').style.display = 'inline-block';
  });
}

//Movement of Freelancer's form
const allFreelancerFrom = document.querySelectorAll('.step');
const formBtnNext = document.querySelectorAll('#formBtn-next');
const formBtnPrev = document.querySelectorAll('#formBtn-prev');
const mainForm = document.querySelector('#mainform');

formBtnNext.forEach((button) => {
  button.addEventListener('click', (e) => {
    changeStep('next');
  });
});
formBtnPrev.forEach((button) => {
  button.addEventListener('click', (e) => {
    changeStep('prev');
  });
});

let i = 0;

function changeStep(btn) {
  if (btn === 'next') {
    i++;
    allFreelancerFrom[i].style.display = 'block';

    allFreelancerFrom[i - 1].style.display = 'none';
  } else if (btn === 'prev') {
    if (i > 0) {
      i--;
      allFreelancerFrom[i].style.display = 'block';
      allFreelancerFrom[i + 1].style.display = 'none';
    }
  }
}

//The multi section form
const cardForm = document.getElementById('cardform');
const cards = document.querySelectorAll('.multistep');
const cardBtnNext = document.querySelectorAll('#btn-next');
const cardBtnPrev = document.querySelectorAll('#btn-prev');
const formProgressBox = [...document.querySelectorAll('.form-progress-box')];
const formProgressText = [...document.querySelectorAll('.form-progress-text')];

if (cardBtnNext) {
  cardBtnNext.forEach((button) => {
    button.addEventListener('click', (e) => {
      moveCard('next');
    });
  });
}

if (cardBtnPrev) {
  cardBtnPrev.forEach((button) => {
    button.addEventListener('click', (e) => {
      moveCard('prev');
    });
  });
}
// cards[0].style.display = 'block';
let index = 0;
let progress = 20;
const progressionBar = document.querySelector('.progress__bar--bar');
function moveCard(btn) {
  if (btn === 'next') {
    index++;
    progress = progress + 20;
    progressionBar.style.width = `${progress}%`;
    cards[index].style.display = 'block';

    cards[index - 1].style.display = 'none';
  } else if (btn === 'prev') {
    if (index > 0) {
      index--;
      cards[index].style.display = 'block';
      cards[index + 1].style.display = 'none';
      if (progress > 19) {
        progress = progress - 20;
        progressionBar.style.width = `${progress}%`;
      }
    }
  }

  // cards
}

//Client image upload
const bgImg = document.querySelector('.client-upload'); // the bg where the uploaded profile would stay
const img_input = document.querySelector('#profile-picture'); // the input type of file

if (img_input) {
  img_input.onchange = (e) => {
    // so when the input experiences a change that it, it has clicked
    let file = e.target.files[0]; // the file variable here holds the image you selected from your file explorer
    let url = URL.createObjectURL(file); // the createObjectURL is a method used to create for the specified object which in our case is a file, so the url variable holds the url to this file(image) we have selected
    bgImg.style.background = `url(${url}) center / cover`; // we select the bg that holds the image and set its CSS bg to the url that we have from the url table
    bgImg.querySelector('.profile-picture').style.display = 'none'; // then we remove the person icon that is on the background
  };
}

// The Accordion with a form element

const servicesBtn = document.querySelectorAll('.services-head');
const serviceBtn = document.querySelector('.services-head');
const editIcon = document.querySelector('.edit-icon');

// The accordion element
if (serviceBtn) {
  servicesBtn.forEach((button) => {
    button.addEventListener('click', (e) => {
      editIcon.style.opacity = 1;
      const serviceContent = button.nextElementSibling;
      button.classList.toggle('service-content-active');

      if (button.classList.contains('service-content-active')) {
        serviceContent.style.maxHeight = serviceContent.scrollHeight + 'px';
        console.log(serviceContent.scrollHeight);
      } else {
        serviceContent.style.maxHeight = 0;
      }
    });
  });
}
const selectyear = document.querySelector('.open-pop');
if (selectyear) {
  selectyear.addEventListener('click', () => {
    document.getElementById('popupI').style.display = 'block';
  });
}
// Custom file upload function
// const uploadBtn = [...document.querySelectorAll('#upload')];
// const uploadBtn1 = document.getElementById('#upload-1');

// const uploadText = [...document.querySelectorAll('#custom-file-upload-text')];
// if (uploadBtn) {
//   uploadBtn[0].addEventListener('change', (e) => {
//     uploadText[0].innerText = e.target.files[0].name;
//   });
//   uploadBtn[1].addEventListener('change', (e) => {
//     uploadText[1].innerText = e.target.files[0].name;
//   });
// }
