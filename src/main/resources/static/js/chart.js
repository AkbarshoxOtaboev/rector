// Запрос данных с бэка
fetch('/admin/status')
    .then(response => response.json())
    .then(data => {
        const ctx = document.getElementById("pie-chart");

        new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ["Yangi", "Jarayonda", "Qabul qilganlar", "Rad qilinganlar"],
                datasets: [{
                    label: "Arizalar soni",
                    backgroundColor: ["#fb9518", "#473a40", "#5cc148", "#fa5c4e"],
                    data: [
                        data.newApplicationCount,
                        data.inProgressApplicationCount,
                        data.completedApplicationCount,
                        data.rejectedApplicationCount
                    ]
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Arizalar Holati'
                }
            }
        });
    })
    .catch(error => console.error('Xatolik yuz berdi:', error));

new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
        labels: ["Urganch shahri", "Urganch tumani", "Bog`ot tumani", "Gurlan tumani", "Qo`shkopir tumani"
            , "Xiva shahri", "Xiva tumani", "Xazarasp tumani", "Xonqa tumani", "Shovot tumani",
            "Yangiariq tumani", "Yangibozor tumani", "Tuproqqala tumani",""],
        datasets: [
            {
                label: "Arizalar",
                backgroundColor: ["#03a9f4", "#e861ff","#08ccce","#e2b35b","#36a2eb",
                    "#9eb797","#8e3ea9","#5d68b2","#f3ed88","#4bc0c0",
                    "#e40503","#49104d","#ff7300","#f3cfcf"],
                data: [15,8,6,10,8,3,6,8,2,9,2,4,8,0]
            }
        ]
    },
    options: {
        legend: { display: false },
        title: {
            display: true,
            text: 'Xorazm viloyati tumanlar kesimida arizalar statistikasi'
        }
    }
});
