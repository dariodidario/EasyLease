const cleaveCC = new Cleave("#cardNumber", {
  creditCard: true,
  delimiter: "-",
  onCreditCardTypeChanged: function (type) {
    const cardBrand = document.getElementById('cardBrand'),
        visa = "fab fa-cc-visa",
        mastercard = "fab fa-cc-mastercard";

    switch (type) {
      case "visa":
        cardBrand.setAttribute("class", visa);
        break;
      case "mastercard":
        cardBrand.setAttribute("class", mastercard);
        break;
      default:
        cardBrand.setAttribute("class", '');
        break;
    }
  },
});

const cleaveDate = new Cleave('#cardExpiry', {
  date: true,
  datePattern: ["m", "y"],
});

const cleaveCCV = new Cleave("#cardCcv", {
  blocks: [3],
  numericOnly: true,
});