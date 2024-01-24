# Options framework
In console applications, we often need to process and read options from the command line.
Since this is essentially a routine activity, i createdd a library for this purpose,
which completely covers this functionality with a suitable abstraction and thus speed up the work of the developer. Use
of this library (defining and reading options) is intuitive and do not require too much configuration code.

## Parameter specification:
Use ``-h`` or ``--help`` to get all defined and predefined options
### Short option
  An argument starting with a dash followed by a single character (ex. ``-v``)
### Long option
  An argument beginning with two dashes followed by one or more characters (ex. ``--version``) 
### Option parameter
  Argument following an option if the option accepts a parameter (ex. ``f test.txt``)
  
## Example input:
``-n 10 --source foo.txt --dest bar.txt --local DE --skipError --switch on``
