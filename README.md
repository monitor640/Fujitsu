# Fujitsu internship project submission

Uncomment the contents of run method in DataLoader.java to populate database with default values. <br>
There are 8 urls to interact with the program: <br>
Lets you add a new base fee for a city and car combo <br>
/addRBF/{city}/{vehicle}/{price} <br>
Gets the total price for a city and vehicle combo, based on the latest weather data <br>
/getTotal/{city}/{vehicle} <br>
Adds a new ATEF with a range and price for that range <br>
/addATEF/{lower}/{upper}/{price} <br>
Deletes an ATEF based on its id <br>
/deleteATEF/{id} <br>
Adds a WSEF in the same way an ATEF is added <br>
/addWSEF/{lower}/{upper}/{price} <br>
Deletes an ATEF based on its id <br>
/deleteWSEF/{id} <br>
Adds a WPEF and phenomenon database entry, database accepts many phenomena per price, but url currently only lets you add one at a time <br>
/addWPEF/{phenomenon}/{price} <br>
Deletes a WPEF and its associated phenomena based on the WPEFs id <br>
/deleteWPEF/{id} <br>

As a remark its currently needed to set a big price e.g 99+ for a range where you want the delivery to be forbidden for bikes and scooters. <br>
Also setting ranges like (-∞;10) or (5;∞) is impossible and the infity needs to be replaced with something like a MAX_INT or obscenely large number. <br>
Further information is provided in the project file comments. <br>
