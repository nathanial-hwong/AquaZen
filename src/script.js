//calls add customproduct if enter key is pressed in product name field (for scanner to work)
document.addEventListener("DOMContentLoaded", function() {
    // Add event listener here
    document.getElementById("barcode-or-name").addEventListener("keypress", function(event) {
        // Check if the pressed key is "Enter"
        if (event.key === "Enter") {
            // Prevent the default behavior of the Enter key (e.g., submitting a form)
            event.preventDefault();
            
            // Add the product
            addCustomProduct();
        }
    });
});
function addCustomProduct() {
    // Retrieve values from input fields
    var barcodeOrName = document.getElementById("barcode-or-name").value;
    var price = document.getElementById("custom-price").value;
    var amount = document.getElementById("custom-amount").value;

    // Default amount to 1 if not specified
    if (!amount) {
        amount = 1;
    }

    // Create a new product element
    var productElement = document.createElement("p");

    // Set the product details text
    productElement.textContent = barcodeOrName + " - $" + price + " x " + amount;

    // Create a remove button for the product
    var removeButton = document.createElement("button");
    removeButton.textContent = "Remove";
    removeButton.onclick = function() {
        productElement.remove(); // Remove the product when the button is clicked
        updateTotal(); // Update the total after removing a product
    };

    // Append the remove button to the product element
    productElement.appendChild(removeButton);

    // Append the product element to the scanned products section
    document.getElementById("scanned-products").appendChild(productElement);

    // Update the total
    updateTotal();

    // Clear input fields after adding the product
    clearCustomFields();
}

function clearCustomFields() {
    document.getElementById("barcode-or-name").value = "";
    document.getElementById("custom-price").value = "";
    document.getElementById("custom-amount").value = "";
}

function updateTotal() {
    var total = 0;
    var products = document.getElementById("scanned-products").getElementsByTagName("p");
    for (var i = 0; i < products.length; i++) {
        var productText = products[i].textContent;
        var price = parseFloat(productText.split("$")[1].split(" x ")[0]);
        var amount = parseFloat(productText.split(" x ")[1]);
        total += price * amount;
    }
    document.getElementById("total").textContent = "Total: $" + total.toFixed(2);
}

function submitPayment() {
    // Add functionality to submit payment
    alert("Payment submitted!");
}

function removeProduct(button) {
    // Get the parent <p> element containing the product details
    var productElement = button.parentNode;
    // Remove the product from the DOM
    productElement.remove();
}

function viewBill() {
    // Get all the product elements
    var products = document.getElementById("scanned-products").getElementsByTagName("p");
    
    // Initialize arrays to store the details of each item
    var itemNames = [];
    var itemPrices = [];
    var itemQuantities = [];

    // Iterate over each product element
    for (var i = 0; i < products.length; i++) {
        var productText = products[i].textContent;
        var itemName = productText.split(" - ")[0]; // Extract item name
        var price = parseFloat(productText.split("$")[1].split(" x ")[0]); // Extract price
        var quantity = parseFloat(productText.split(" x ")[1]); // Extract quantity

        // Add item details to the respective arrays
        itemNames.push(itemName);
        itemPrices.push(price);
        itemQuantities.push(quantity);
    }

    // Calculate subtotal
    var subtotal = itemPrices.reduce((acc, price, index) => acc + price * itemQuantities[index], 0);

    // Construct the URL for the bill page with the necessary parameters
    var billURL = "bill.html?";
    // Append each item's details and subtotal to the URL as parameters
    itemNames.forEach(function(itemName, index) {
        billURL += "itemName" + (index + 1) + "=" + encodeURIComponent(itemName) + "&";
        billURL += "itemPrice" + (index + 1) + "=" + encodeURIComponent(itemPrices[index]) + "&";
        billURL += "itemQuantity" + (index + 1) + "=" + encodeURIComponent(itemQuantities[index]) + "&";
    });
    billURL += "subtotal=" + encodeURIComponent(subtotal);

    // Redirect to the bill page
    window.location.href = billURL;
}
