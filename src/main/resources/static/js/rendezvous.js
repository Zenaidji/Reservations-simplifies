function getCurrentDate() {
    let today = new Date();
    let year = today.getFullYear();
    let month = String(today.getMonth() + 1).padStart(2, '0');
    let day = String(today.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function getEndOfMonth() {
    let today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let lastDay = new Date(year, month, 0).getDate();
    let lastMonth = String(month).padStart(2, '0');
    let day = String(lastDay).padStart(2, '0');
    return `${year}-${lastMonth}-${day}`;
}

$(document).ready(function () {
    $('#date').attr('min', getCurrentDate());
    $('#date').attr('max', getEndOfMonth());
    $('#date').val(getCurrentDate());

   $(document).on('click', '.list-group-item', function () {
        $('.list-group-item').removeClass('active');
        $(this).addClass('active');
        var idCreneau = $(this).attr('id');
        $('#creneauSelectionne').val(idCreneau);
        console.log(idCreneau);
    });



    /*  $('#date').on('change', function() {
          getCrenauxParDate();
      });*/
});


function getCrenauxParDate() {
    var dateActuelle = $('#date').val();

    var boutonConfirmer = $('.btn-primary:contains("Confirmer le rendez-vous")');
    var crenauxList = $('#crenauxList');
    crenauxList.empty();
    $('#Norendezvous').html('');

    $.ajax({
        url: '/getAllCreneauxParDate',
        type: 'GET',
        data: { date: dateActuelle },
        success: function (response) {
            if(response && response.length > 0) {

                boutonConfirmer.prop('disabled', false);

              console.log('toto');
                response.forEach(function (creneau) {
                    var heureDebut = creneau.heureDebut.substr(0, 5);
                    var heureFin = creneau.heureFin.substr(0, 5);
                    var horaire = heureDebut + ' - ' + heureFin;
                    var listItem = `<li class="list-group-item list-group-item-action text-center" id="${creneau.id}">${horaire}</li>`;
                    crenauxList.append(listItem);
                });
            } else {
                boutonConfirmer.prop('disabled', true);
                $('#Norendezvous').html('<span class="material-symbols-outlined col-2" style="font-size: 48px; color:red"> event_busy </span>    <p class="col-10 mt-1">Aucun créneau  est disponible pour cette date</p>');

            }
        },
        error: function (xhr, status, error) {
            console.error('Erreur:', error);
            boutonConfirmer.prop('disabled', true);
            $('#Norendezvous').html('<p class="text-center text-danger">Erreur lors de la récupération des créneaux.</p>');
        }
    });
}





function confirmerRendezVous(event) {
    var form = $('#rendezvousForm')[0];
    if (form.checkValidity()) {
        event.preventDefault();
        var formData = new FormData(form);
        fetch('/submit-form', {
            method: 'POST',
            body: formData,
        })
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Erreur lors de l\'envoi des données.');
                }
            })
            .then(data => {
                document.body.innerHTML = data;
                form.reset();
                $(form).removeClass('was-validated');
            })
            .catch(error => {
                console.error('Erreur:', error);
                alert("Une erreur s'est produite lors de l'envoi du formulaire.");
            });
    } else {
        form.reportValidity();
    }
}


