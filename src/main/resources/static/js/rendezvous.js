


$(document).ready(function () {

    initDatepicker();
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


function initDatepicker() {
    $.ajax({
        url: "/getDatesDisponibles",
        type: "GET",
        success: function (datesDisponibles) {
            // Transformer le tableau en Set pour lookup rapide
            let datesSet = new Set(datesDisponibles);

            $("#date").datepicker({
                dateFormat: "yy-mm-dd",   // format YYYY-MM-DD
                minDate: 0,               // aujourd’hui
                maxDate: "+30D",          // +30 jours
                beforeShowDay: function (date) {
                    let y = date.getFullYear();
                    let m = String(date.getMonth() + 1).padStart(2, '0');
                    let d = String(date.getDate()).padStart(2, '0');
                    let formatted = `${y}-${m}-${d}`;

                    if (datesSet.has(formatted)) {
                        return [true, "disponible-date", "Disponible"]; // activé + CSS
                    } else {
                        return [false, "", "Indisponible"]; // désactivé
                    }
                }
            });
        },
        error: function () {
            console.error("Erreur lors de la récupération des dates disponibles");
        }
    });
}

