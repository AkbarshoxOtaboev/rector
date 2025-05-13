function changeLanguage(lang) {
    let button = document.getElementById('languageDropdown');
    if (lang === 'uz') {
        button.innerHTML = `<img src="https://flagcdn.com/w40/uz.png" width="20" alt="Uzbek Flag"> Uzbek`;
    } else if (lang === 'ru') {
        button.innerHTML = `<img src="https://flagcdn.com/w40/ru.png" width="20" alt="Russian Flag"> Russian`;
    } else if (lang === 'en') {
        button.innerHTML = `<img src="https://flagcdn.com/w40/us.png" width="20" alt="US Flag"> English`;
    }
}

document.getElementById('regions').addEventListener('change', function () {
    const regionId = this.value;
    const districtSelect = document.getElementById('districts');
    districtSelect.innerHTML = '<option value="">Tanlang...</option>';
    fetch(`/api/districts?regionId=${regionId}`)
        .then(response => response.json())
        .then(data => {
            data.forEach(district => {
                const option = document.createElement('option');
                option.value = district.id;
                option.text = district.name;
                districtSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Ошибка загрузки районов:', error);
            districtSelect.innerHTML = '<option value="">Ошибка загрузки</option>';
        });

});

document.addEventListener('DOMContentLoaded', function() {
    const offer = document.getElementById('offer');
    const offerText = document.getElementById('offerText');

    offer.addEventListener('change', function() {
        if (this.checked) {
            offerText.style.display = 'block';
        } else {
            offer.style.display = 'none';
        }
    });


    const organization = document.getElementById('organization');
    const organizationInput = document.getElementById('organizationInput');
    const organizationNameInput = document.getElementById('organizationName');

    organization.addEventListener('change', function() {
        if (this.checked) {
            organizationInput.style.display = 'block';
            organizationNameInput.setAttribute('required', 'required');
        } else {
            organizationInput.style.display = 'none';
            organizationNameInput.removeAttribute('required');
            organizationNameInput.value = '';
        }
    });
});
