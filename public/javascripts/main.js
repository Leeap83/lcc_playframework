 window.onload = function() {
    var el = document.getElementById('g-recaptcha-response');
    if (el) {
        el.setAttribute('required', 'required');
    }
}
window.onbeforeunload = () => {
    for(const form of document.getElementsByTagName('form')) {
    form.reset();
    }
}