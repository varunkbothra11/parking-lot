# Parking lot
A simplistic approach to solve the parking lot design problem. The solution follows object oriented design principles.

# Assumptions

- Size of all the vehicles is same.
- There is only one floor in the parking lot having n slots to park cars.
- There is no support for paying the parking fee. For now it is a simple system that can place vehicle in a parking slot and empty the slot based on mentioned conditions.
- All the staring and trailing spaces will be trimmed while processing the commands.
- Command line arguments will take priority over the interactive mode.
- As of now only the following colors are supported: RED, GREEN, BLUE, BLACK, WHITE. To support more colors we can add the colors to Color.java enum.
- Number of slots per floor is in range of Integer.

# Building and running code
- Navigate to the root parking_lot directory.
- Run the command `bin/setup` => This will install the dependencies and build the source code and run all the unit tests and integration tests part of the project.
- Run the command `bin/run_functional_tests` => This will run all the function tests specified in the project
- Run the command `bin/parking_lot` => This will run the project in command line mode, passing file will execute the commands specified in the file