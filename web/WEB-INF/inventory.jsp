
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Inventory</title>
    </head>
    <body>
        <h1>Home Inventory for ${user}</h1>
        <h2>Add Item</h2>
        <form method="post" action="">
            <label>Category</label>
            <select name="category" value="">
                <option>bedroom</option>
                <option>garage</option>
                <option>kitchen</option>
                <option>living room</option>
            </select>
            <br>
            <label>Item Name:</label>
            <input type="text" name="item_name" value="">
            <br>
            <label>Price:</label>
            <input type="text" name="item_price" value="">
            <br>
            <br>
            <input type="submit" value="Add"> ${errorMessage}
        </form>
        <p>${successMessage}</p>
        <p>${totalMessage}</p>
        <a href="login?logout">Logout</a>
        
    </body>
</html>
