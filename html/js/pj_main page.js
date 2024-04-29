// 네비게이션 바 토글 버튼 기능
const toggleBtn = document.querySelector('.navbar__toggleBtn');
const menu = document.querySelector('.navbar__menu');

toggleBtn.addEventListener('click', () => {
  menu.classList.toggle('active');
});