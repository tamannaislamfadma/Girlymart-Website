// ===== HERO SLIDER =====
let heroIndex = 0;
function changeSlide(dir, type) {
    const slides = document.querySelectorAll('.slide');
    if (!slides.length) return;
    heroIndex = (heroIndex + dir + slides.length) % slides.length;
    document.querySelector('.slides').style.transform = `translateX(-${heroIndex * 100}%)`;
}
setInterval(() => changeSlide(1, 'hero'), 4000);

// ===== CROCHET DROPDOWN =====
function toggleCrochet() {
    const menu = document.getElementById('crochetMenu');
    if (menu) menu.classList.toggle('open');
}

// ===== CLOSE MODAL ON OUTSIDE CLICK =====
window.addEventListener('click', function(e) {
    const modal = document.getElementById('reviewModal');
    if (modal && e.target === modal) {
        modal.style.display = 'none';
    }
});