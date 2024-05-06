function addCustomProduct() {
    // Retrieve values from input fields
    var productName = document.getElementById("product-name").value;
    var customProductName = document.getElementById("custom-product-name").value;
    var price = document.getElementById("custom-price").value;
    var amount = document.getElementById("custom-amount").value;

    // Default amount to 1 if not specified
    if (!amount) {
        amount = 1;
    }

    // Create a new product element
    var productElement = document.createElement("p");

    // Determine the displayed product name
    var displayedProductName = productName === "other" && customProductName ? customProductName : productName;

    // Set the product details text
    productElement.textContent = displayedProductName + " - $" + price + " x " + amount;

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

function clearCustomFields() {
    document.getElementById("product-name").value = "livestock";
    document.getElementById("custom-product-name").value = "";
    document.getElementById("custom-price").value = "";
    document.getElementById("custom-amount").value = "";
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