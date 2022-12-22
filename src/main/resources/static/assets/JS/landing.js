const menuBtn = document.querySelector('.menu-btn');
const closeBtn = document.querySelector('.close-btn');

const header = document.querySelector('.header');
if (menuBtn) {
  menuBtn.addEventListener('click', function () {
    header.classList.add('nav-open');
  });
}

closeBtn.addEventListener('click', function () {
  header.classList.remove('nav-open');
});
document.querySelectorAll('.nav__item').forEach((item) =>
  item.addEventListener('click', () => {
    header.classList.remove('nav-open');
  })
);
const accordionContent = document.querySelectorAll('.faq');

accordionContent.forEach((item, index) => {
  let header = item.querySelector('.faq-question');
  header.addEventListener('click', () => {
    item.classList.toggle('open');

    let description = item.querySelector('.hidden-answer');
    if (item.classList.contains('open')) {
      description.style.height = `${description.scrollHeight}px`; //scrollHeight property returns the height of an element including padding , but excluding borders, scrollbar or margin
    } else {
      description.style.height = '0px';
    }
    removeOpen(index); //calling the funtion and also passing the index number of the clicked header
  });
});

function removeOpen(index1) {
  accordionContent.forEach((item2, index2) => {
    if (index1 != index2) {
      item2.classList.remove('open');

      let des = item2.querySelector('.hidden-answer');
      des.style.height = '0px';
    }
  });
}

const counters = document.querySelectorAll('.count-num');

function runCount() {
  counters.forEach((counter) => {
    counter.innerText = '0';

    const updateCounter = () => {
      const target = counter.getAttribute('data-target');
      const c = +counter.innerText;
      const increment = target / 500;

      if (c < target) {
        const num = `${Math.ceil(c + increment)}`;
        counter.innerText = num;
        setTimeout(updateCounter, 1);
      } else {
        counter.innerText = +target;
      }
    };
    updateCounter();
  });
}
// HIRE BUTTON TOGGLE
const hiddenEl = document.querySelectorAll('.hidden');
const observer = new IntersectionObserver((entries) => {
  entries.forEach((ent) => {
    if (ent.isIntersecting) {
      ent.target.classList.add('show');
      if (ent.target.classList.contains('about-section')) {
        runCount();
      }
    } else {
      ent.target.classList.remove('show');
    }
  });
});

hiddenEl.forEach((el) => observer.observe(el));

// tweaks
document.querySelector('.company-input').addEventListener('input', function () {
  if (this.value === '') {
    document.querySelector('.caution-text').style.display = 'block';
  } else {
    document.querySelector('.caution-text').style.display = 'none';
  }
});

document.querySelector('.msg').addEventListener('input', function () {
  if (this.value === '') {
    document.querySelector('.limit-text').style.display = 'block';
  } else {
    document.querySelector('.limit-text').style.display = 'none';
  }
});
