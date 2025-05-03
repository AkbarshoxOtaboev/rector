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

document.addEventListener('DOMContentLoaded', function() {
    const offerCheckbox = document.getElementById('offer');
    const offerText = document.getElementById('offerText');
    const regionSelect = document.getElementById("region");
    const villageSelect = document.getElementById("district");
    let villagesData = []; // will store villages
    fetch('/json/villages.json')
        .then(response => response.json())
        .then(data => {
            data.regions.forEach(region => {
                const option = document.createElement("option");
                option.id = region.id
                option.value = region.name;
                option.textContent = region.name;
                regionSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Regions yuklanishida xatolik:', error);
        });

    // Load all villages once
    fetch('/json/villages.json')
        .then(response => response.json())
        .then(data => {
            villagesData = data.districts;
        })
        .catch(error => {
            console.error('Villages yuklanishida xatolik:', error);
        });

    regionSelect.addEventListener('change', () => {
        console.log(regionSelect.options.getAttribute("id"))
        const selectedRegionId = parseInt(regionSelect.options.id);

        // Filter villages that belong to selected region
        const filteredVillages = villagesData.filter(village => village.region_id === selectedRegionId);

        // Add villages to dropdown
        filteredVillages.forEach(village => {
            const option = document.createElement("option");
            option.value = village.id;
            option.textContent = village.name;
            villageSelect.appendChild(option);
        });
    });


    offerCheckbox.addEventListener('change', function() {
        if (this.checked) {
            offerText.style.display = 'block';
        } else {
            offerText.style.display = 'none';
        }
    });


    const organizationCheckbox = document.getElementById('organization');
    const organizationInput = document.getElementById('organizationInput');
    const organizationNameInput = document.getElementById('organizationName');

    organizationCheckbox.addEventListener('change', function() {
        if (this.checked) {
            organizationInput.style.display = 'block';
            organizationNameInput.setAttribute('required', 'required');
        } else {
            organizationInput.style.display = 'none';
            organizationInput.removeAttribute('required');
            organizationNameInput.value = '';
        }
    });


});