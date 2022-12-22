//CONTENT BTNS

const detailsBtn = document.getElementById('details');
const jobsBtn = document.getElementById('jobs');
const transBtn = document.getElementById('transactions');

// CONTENT BOX
const detailsBox = document.querySelector('.clientdetailsbox');
const jobsBox = document.querySelector('.clientsjobsbox');
const transBox = document.querySelector('.client-transaction-box');

document.querySelectorAll('.admin-link-item').forEach((link) => {
  if (link.href === window.location.href) {
    link.parentElement.style.background = 'var(--admin-primary)';
  }
});
if (detailsBtn) {
  detailsBtn.addEventListener('click', function () {
    this.classList.add('active-freelancer');
    detailsBox.style.display = 'block';

    jobsBtn.classList.remove('active-freelancer');
    jobsBox.style.display = 'none';

    transBtn.classList.remove('active-freelancer');
    transBox.style.display = 'none';
  });
}

if (jobsBtn) {
  jobsBtn.addEventListener('click', function () {
    this.classList.add('active-freelancer');
    jobsBox.style.display = 'block';

    detailsBtn.classList.remove('active-freelancer');
    detailsBox.style.display = 'none';

    transBtn.classList.remove('active-freelancer');
    transBox.style.display = 'none';
  });
}
if (transBtn) {
  transBtn.addEventListener('click', function () {
    this.classList.add('active-freelancer');
    transBox.style.display = 'block';

    jobsBtn.classList.remove('active-freelancer');
    jobsBox.style.display = 'none';

    detailsBtn.classList.remove('active-freelancer');
    detailsBox.style.display = 'none';
  });
}

// ADMIN  JOBS

// /////////
// MENU
const adminMenu = document.querySelector('.menu-btn');
const adminContainer = document.querySelector('.admin-overview');
if (adminMenu) {
  adminMenu.addEventListener('click', () =>
    adminContainer.classList.toggle('adminNav-open')
  );
}
document.querySelector('.admin-overlay').addEventListener('click', () => {
  adminContainer.classList.remove('adminNav-open');
});

const paginationNumbers = document.getElementById('pagination-numbers');
if (paginationNumbers) {
  const paginatedList = document.getElementById('paginated-list');
  const listItems = paginatedList.querySelectorAll('tr');
  const nextButton = document.getElementById('next-button');
  const prevButton = document.getElementById('prev-button');

  const paginationLimit = 10;

  const pageCount = Math.ceil(listItems.length / paginationLimit);
  let currentPage = 1;

  const disableButton = (button) => {
    button.classList.add('disabled');
    button.setAttribute('disabled', true);
  };

  const enableButton = (button) => {
    button.classList.remove('disabled');
    button.removeAttribute('disabled');
  };

  const handlePageButtonsStatus = () => {
    if (currentPage === 1) {
      disableButton(prevButton);
    } else {
      enableButton(prevButton);
    }

    if (pageCount === currentPage) {
      disableButton(nextButton);
    } else {
      enableButton(nextButton);
    }
  };

  const handleActivePageNumber = () => {
    document.querySelectorAll('.pagination-number').forEach((button) => {
      button.classList.remove('active');
      const pageIndex = Number(button.getAttribute('page-index'));
      if (pageIndex == currentPage) {
        button.classList.add('active');
      }
    });
  };

  const appendPageNumber = (index) => {
    const pageNumber = document.createElement('button');
    pageNumber.className = 'pagination-number';
    pageNumber.innerHTML = index;
    pageNumber.setAttribute('page-index', index);
    pageNumber.setAttribute('aria-label', 'Page ' + index);
    paginationNumbers.appendChild(pageNumber);
  };

  const getPaginationNumbers = () => {
    for (let i = 1; i <= pageCount; i++) {
      appendPageNumber(i);
    }
  };

  const setCurrentPage = (pageNum) => {
    currentPage = pageNum;

    handleActivePageNumber();
    handlePageButtonsStatus();

    const prevRange = (pageNum - 1) * paginationLimit;
    const currRange = pageNum * paginationLimit;

    listItems.forEach((item, index) => {
      item.classList.add('hidden');
      if (index >= prevRange && index < currRange) {
        item.classList.remove('hidden');
      }
    });
  };

  window.addEventListener('load', () => {
    getPaginationNumbers();
    setCurrentPage(1);

    prevButton.addEventListener('click', () => {
      setCurrentPage(currentPage - 1);
    });

    nextButton.addEventListener('click', () => {
      setCurrentPage(currentPage + 1);
    });

    document.querySelectorAll('.pagination-number').forEach((button) => {
      const pageIndex = Number(button.getAttribute('page-index'));

      if (pageIndex) {
        button.addEventListener('click', () => {
          setCurrentPage(pageIndex);
        });
      }
    });
  });
}
// //// admin profile
const updateBtn = document.getElementById('update-btn');
const closePasswordBtn = document.getElementById('close');
const passwordForm = document.querySelector('.changePasswordForm');
if (updateBtn) {
  updateBtn.addEventListener('click', () => {
    passwordForm.classList.add('open');
  });

  closePasswordBtn.addEventListener('click', () => {
    passwordForm.classList.remove('open');
  });
}
document.querySelector('.all-filter').addEventListener('click', function () {
  this.classList.toggle('open');
});
const optionsAllInput = document.querySelector('.all-input');
const allOptionT = document.querySelectorAll('.option-text');

allOptionT.forEach((item) =>
  item.addEventListener('click', function () {
    optionsAllInput.value = this.textContent;
  })
);

// ADMIN IMG
function uploadedImg(input) {
  $('#admin-profile')[0].src = (window.URL ? URL : webkitURL).createObjectURL(
    input.files[0]
  );
}
