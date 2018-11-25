```
   _____                    _ _             _             
  / ____|                  | (_)           | |            
 | |     ___   ___  _ __ __| |_ _ __   __ _| |_ ___  ___  
 | |    / _ \ / _ \| '__/ _` | | '_ \ / _` | __/ _ \/ __| 
 | |___| (_) | (_) | | | (_| | | | | | (_| | ||  __/\__ \ 
  \_____\___/ \___/|_|  \__,_|_|_|_|_|\__,_|\__\___||___/ 
  / ____|                        | |                      
 | |     ___  _ ____   _____ _ __| |_ ___ _ __            
 | |    / _ \| '_ \ \ / / _ \ '__| __/ _ \ '__|           
 | |___| (_) | | | \ V /  __/ |  | ||  __/ |              
  \_____\___/|_| |_|\_/ \___|_|   \__\___|_|              
```

# Coordinates Converter 

This very small project converts a set of Cartesian coordinates to the Geographic Coordinate System WGS84.

WGS84 is an Earth-centered, Earth-fixed terrestrial reference system and geodetic datum.
WGS84 is based on a consistent set of constants and model parameters that describe
the Earth's size, shape, and gravity and geomagnetic fields.
WGS84 is the standard U.S. Department of Defense definition of a global reference system
for geospatial information and is the reference system for the Global Positioning System (GPS).

All directions below are for Unix based systems. 
For Windows use the corresponding *.bat scripts.

## Building & Running Unit Tests 
Clone the project. At the root of the project run:
```$bash
./gradlew clean build 
```

## Creating an executable application
At the root of the project run:
```$bash
./gradlew clean build installDist
```

This creates the following directory: 
```$bash
build/install/CoordinatesConverter
```

It contains a ```lib``` directory with all required jars, and a ```bin``` directory with the appropriate 
scripts to run the application in Unix based systems (```Coordinates Converter```) 
and Windows systems (```Coordinates Converter.bat```).


### Running the application
Copy the ```CoordinatesConverter``` directory from ```build/install/``` to a place of your choice. Then: 
```$bash
cd CoordinatesConverter/bin/
```
And run the application with a set of Cartesian Coordinates. 

For example running for Cartesian Coordinates ```(6378137, 0, 0)``` gives us the GCS point ```(0, 0, 0)```:  
```$bash
./CoordinatesConverter 6378137 0 0
``` 
Produces: 
```
Latitude: 0.0 
Longitude: 0.0 
Elevation: 0.0
``` 



