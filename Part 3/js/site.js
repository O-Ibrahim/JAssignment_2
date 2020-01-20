let moneyInside = 0;
let transaction = 0;
let change = 0;
let stock = 10;
let selectedItem = null;
let items = [
  {
    id: 1,
    name: "Chewing Gum",
    price: 0.5,
    stock
  },
  {
    id: 2,
    name: "Chips",
    price: 1,
    stock
  },
  {
    id: 3,
    name: "Chocolate",
    price: 2,
    stock
  }
];
const acceptedMonetaryValues = [0.25, 0.5, 1, 5, 10];

const addMoneyToTransaction = money => {
  transaction += money;
  changeScreenInnerText("transactionText", `${transaction} JD`);
};

const buy = () => {
  if (transaction === 0) {
    alert("Please insert money to begin your purchase");
  } else if (selectedItem === null) {
    alert("Please select the item you wish to buy");
  } else {
    //prequisites done
    let { price, stock } = items.find(x => x.id == selectedItem);
    if (stock <= 0) {
      alert("Item out of stock");
    } else if (transaction < price) {
      alert(
        "Insufficient funds, pls insert more money to continue your purchase"
      );
    } else {
      change = transaction - price;
      moneyInside += price;
      transaction = 0;
      reduceStock(selectedItem, 1);
      selectedItem = null;
      renderItems();
      changeScreenInnerText("moneyInsideMachineText", `${moneyInside} JD`);
      changeScreenInnerText("transactionText", `${transaction} JD`);
      changeScreenInnerText("changeText", `${change} JD`);
    }
  }
};

const reduceStock = (id, amount) => {
  items.find((item, i) => {
    if (item.id === id) {
      items[i] = {
        ...item,
        stock: item.stock - amount
      };
      return true; // stop searching
    }
  });
};
const changeScreenInnerText = (target, value) => {
  value = parseFloat(value).toFixed(2);
  document.getElementById(target).innerHTML = value + " JD";
};
const selectItem = id => {
  selectedItem = id;
  renderItems();
};

const renderItems = () => {
  let codeBlock = ``;
  items.forEach(item => {
    codeBlock += `
        <div class="row">
            <div class="col-md-12">
                <button class="btn m-1 item-btn ${
                  selectedItem == item.id
                    ? `btn-primary`
                    : `btn-outline-secondary`
                }" type="button" onclick="selectItem(${item.id})">
                    ${item.name}
                    <br />
                     ${item.price} JD
                     <br />
                     Units in stock: ${item.stock}
                </button>
            </div>
        </div>
    `;
  });
  document.getElementById("items").innerHTML = codeBlock;
};

const reset = () => {
  moneyInside = 0;
  transaction = 0;
  change = 0;
  stock = 10;
  selectedItem = null;
  items = [
    {
      id: 1,
      name: "Chewing Gum",
      price: 0.5,
      stock
    },
    {
      id: 2,
      name: "Chips",
      price: 1,
      stock
    },
    {
      id: 3,
      name: "Chocolate",
      price: 2,
      stock
    }
  ];
  changeScreenInnerText("moneyInsideMachineText", `${moneyInside} JD`);
  changeScreenInnerText("transactionText", `${transaction} JD`);
  changeScreenInnerText("changeText", `${change} JD`);
  renderItems();
};
reset();
