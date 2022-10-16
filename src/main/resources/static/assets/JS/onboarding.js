    const freelanceBox = document.getElementById("Freelance-box")
    const clientBox = document.getElementById("client-box")
    
    
    if(freelanceBox) {
        freelanceBox.addEventListener("mouseover", (e) => {
            document.querySelector('#Freelancer-icon').classList.add('Freelancer-icon')
            
           })
           freelanceBox.addEventListener("mouseout", (e) => {
            document.querySelector('#Freelancer-icon').classList.remove('Freelancer-icon')
            
           })
        freelanceBox.addEventListener('click', (e) => {
            document.getElementById("btn-normal").style.display = 'none';
            document.getElementById("btn-freelancer").style.display = 'inline-block';
            document.getElementById("btn-client").style.display = 'none';
        })
      
    }
   
    if(clientBox) {
        clientBox.addEventListener("mouseover", (e) => {
            document.querySelector('#client-icon').classList.add('client-icon')
            
           })
           clientBox.addEventListener("mouseout", (e) => {
            document.querySelector('#client-icon').classList.remove('client-icon')
            
           })

        clientBox.addEventListener('click', (e) => {
            document.getElementById("btn-normal").style.display = 'none';
            document.getElementById("btn-freelancer").style.display = 'none';
            document.getElementById("btn-client").style.display = 'inline-block'; 
        })
    }
   
//Movement of Freelancer's form 

const allFreelancerFrom = [...document.querySelectorAll(".step")] // used the spread operator to convert the Nodelist from the queryselectorAll to an Array
const formBtnNext = document.querySelectorAll("#formBtn-next");
const formBtnPrev = document.querySelectorAll("#formBtn-prev")
const mainForm = document.querySelector('#mainform');

formBtnNext.forEach(button => {
    button.addEventListener("click", (e) => {
        changeStep('next');
    })
})
formBtnPrev.forEach(button => {
    button.addEventListener("click", (e) => {
        changeStep('prev');
    })
})

function changeStep(btn) {
    let index = 0;
    const active = document.querySelector(".active");
    index = allFreelancerFrom.indexOf(active)
    allFreelancerFrom[index].classList.remove('active');
    if(btn === 'next') {
        index++;
    }
    else if (btn === 'prev') {
        index--;
    }
    allFreelancerFrom[index].classList.add('active');

}

 //The multi section form
const cardForm = document.getElementById("cardform");
const cards = [...document.querySelectorAll(".multistep")];
const cardBtnNext = document.querySelectorAll("#btn-next");
const cardBtnPrev = document.querySelectorAll("#btn-prev");
const formProgressBox = [...document.querySelectorAll(".form-progress-box")]
const formProgressText = [...document.querySelectorAll(".form-progress-text")]

cardBtnNext.forEach(button => {
    button.addEventListener("click", (e) => {
        moveCard('next');
    })
})

cardBtnPrev.forEach(button => {
    button.addEventListener("click", (e) => {
        moveCard('prev')
    })
})

function moveCard(btn) {
    let index = 0;
    let progress = 0;
    let textProgress = 0;
    const activeprogressbox = document.querySelector(".form-progress-active")
    const activeProgressText= document.querySelector(".form-progress-text-active")
    const activeCard = document.querySelector(".active-card");

    index = cards.indexOf(activeCard);
    progress = formProgressBox.indexOf(activeprogressbox)
    textProgress = formProgressText.indexOf(activeProgressText)

    formProgressBox[progress].classList.remove('form-progress-active')
    formProgressText[textProgress].classList.remove('form-progress-text-active')
    cards[index].classList.remove('active-card');
    if(btn === 'next') {
        index++;
        progress++;
        textProgress++;
    }else if(btn === 'prev'){
        index--;
        progress--;
        textProgress--;
    }
    cards[index].classList.add('active-card');
    formProgressBox[progress].classList.add('form-progress-active')
    formProgressText[textProgress].classList.add('form-progress-text-active')
}


//Client image upload
const bgImg =document.querySelector(".client-upload") // the bg where the uploaded profile would stay
const img_input = document.querySelector("#profile-picture") // the input type of file

    if(img_input) {
        img_input.onchange = (e) => { // so when the input experiences a change that it, it has clicked
            let file = e.target.files[0] // the file variable here holds the image you selected from your file explorer
            let url = URL.createObjectURL(file); // the createObjectURL is a method used to create for the specified object which in our case is a file, so the url variable holds the url to this file(image) we have selected
            bgImg.style.background = `url(${url}) center / cover` // we select the bg that holds the image and set its CSS bg to the url that we have from the url table
            bgImg.querySelector('.profile-picture').style.display = "none"; // then we remove the person icon that is on the background
        }
    }
   




// The Accordion with a form element

const serviceBtn = document.querySelectorAll(".services-head") // the accordion element

    if(serviceBtn) {
        serviceBtn.forEach(button => {
            button.addEventListener("click", () => {
                const serviceContent = button.nextElementSibling;
                button.classList.toggle('service-content-active');

                if(button.classList.contains('service-content-active')) {
                    serviceContent.style.maxHeight = serviceContent.scrollHeight + 'px';
                } else {
                    serviceContent.style.maxHeight = 0;
                }
            });
        })
       
    }

// Custom file upload function
const uploadBtn = [...document.querySelectorAll('#upload')];
const uploadBtn1 = document.getElementById('#upload-1');

const uploadText = [...document.querySelectorAll('#custom-file-upload-text')];


// uploadBtn[0].addEventListener('change', (e) => {
//     uploadText[0].innerText = e.target.files[0].name;
// })
uploadBtn[1].addEventListener('change', (e) => {
    uploadText[1].innerText = e.target.files[0].name;
})







