const freelanceBox = document.getElementById('Freelance-box');
const clientBox = document.getElementById('client-box');
const clientIcon = document.querySelector('#client-icon');
const btn = document.querySelector('.lol');
const signModal = document.querySelector('.sign-modal');
const freelancerIcon = document.querySelector('#Freelancer-icon');

// /ONBORADING

function imgUrl(obj, id, btn) {
  document.getElementById(id).click();
  if (obj === null) return;
  const file = obj.value;
  console.log(file);
  const fileName = file.split('\\');
  document.querySelector(btn).innerHTML = fileName[fileName.length - 1];
}

//

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

// Onboarding events "continue as Client" box
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

//   //

// FREELANCER
// The Accordion  form for freelancers logic
const services = document.querySelector('.services-cta');
const serviceBtn = document.querySelectorAll('.services');

// if (serviceBtn) {
//   serviceBtn.forEach((btn, i) =>
//     btn.addEventListener('click', function () {
//       // services.classList.toggle('active-acc');
//       services.classList.toggle('active-acc');
//       // const prevHeight = .nextElementSibling;
//       // const nextHeight = this.previousElementSibling;

//       // if (
//       //   prevHeight.classList.contains('active-acc') ||
//       //   nextHeight.classList.contains('active-acc')
//       // ) {
//       //   prevHeight.classList.remove('active-acc');
//       //   nextHeight.classList.remove('active-acc');
//       // }
//     })
//   );
const servicesBtn = document.querySelectorAll('.services-head');
// const serviceBtn = document.querySelector('.services-head');
// const editIcon = document.querySelector('.edit-icon');
if (serviceBtn) {
  servicesBtn.forEach((button, i) => {
    button.addEventListener('click', (e) => {
      const serviceContent = button.nextElementSibling;
      button.classList.toggle('service-content-active');
      if (button.classList.contains('service-content-active')) {
        serviceContent.style.maxHeight =
          serviceContent.scrollHeight + 10 + 'px';
        button.style.border = '0.4rem solid #151617';
      } else {
        button.style.border = 'none';
        serviceContent.style.maxHeight = 0;
      }
      removeOpen(i);
    });
  });

  function removeOpen(index1) {
    serviceBtn.forEach((item2, index2) => {
      item2.style.border = 'none';
      if (index1 != index2) {
        item2.style.border = 'none';
        const serviceContent = item2.querySelector('.service-content');
        serviceContent.style.maxHeight = 0;
      }
    });
  }
}

// CLIENT;
// uploading a profile picture
const profilePicOnboarding = document.querySelector('.profile-picture');
const cUpload = document.querySelector('.client-upload');
const fUpload = document.querySelector('.file-upload');
function img_pathUrl_fl(input) {
  $('#img_urlfreelancer')[0].src = webkitURL.createObjectURL(input.files[0]);
  profilePicOnboarding.style.width = 'fit-content';
  profilePicOnboarding.style.height = '100%';
  cUpload.style.overflow = 'hidden';
  fUpload.style.display = 'none';
  profilePicOnboarding.style.top = 0;
  profilePicOnboarding.style.left = 0;
  // profilePicOnboarding.style.borderRadius = '50%';
}

const profilePicClient = document.querySelector('.profile-picture');
function img_pathUrl(input) {
  $('#img_pro')[0].src = (window.URL ? URL : webkitURL).createObjectURL(
    input.files[0]
  );
  profilePicOnboarding.style.width = '30rem';
  profilePicOnboarding.style.maxHeight = '30rem';
profilePicOnboarding.style.objectFit = 'cover';
  profilePicOnboarding.style.top = 0;
  profilePicOnboarding.style.left = 0;
  profilePicOnboarding.style.borderRadius = '50%';
}
