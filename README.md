# Fujitsu internship project submission

Uncomment the contents of run method in DataLoader.java to populate database with default values. 
There are 8 urls to interact with the program:
Lets you add a new base fee for a city and car combo
/addRBF/{city}/{vehicle}/{price}
Gets the total price for a city and vehicle combo, based on the latest weather data
/getTotal/{city}/{vehicle}
Adds a new ATEF with a range and price for that range 
/addATEF/{lower}/{upper}/{price}
Deletes an ATEF based on its id
/deleteATEF/{id}
Adds a WSEF in the same way an ATEF is added
/addWSEF/{lower}/{upper}/{price}
Deletes an ATEF based on its id
/deleteWSEF/{id}
Adds a WPEF and phenomenon database entry, database accepts many phenomena per price, but url currently only lets you add one at a time
/addWPEF/{phenomenon}/{price}
Deletes a WPEF and its associated phenomena based on the WPEFs id
/deleteWPEF/{id}

As a remark its currently needed to set a big price e.g 99+ for a range where you want the delivery to be forbidden for bikes and scooters. 
Also setting ranges like (-∞;10) or (5;∞) is impossible and the infity needs to be replaced with something like a MAX_INT or obscenely large number.
Further information is provided in the project file comments.
