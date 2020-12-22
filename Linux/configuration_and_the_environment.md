## The environment
- `printenv`: print part or all of the environment
- `set`: set shell options
- `export`: export environment to subsequently executed programs
- `alias`: create an alias for a command
- `source ~/.bash_profile` activating our changes
- the shell stores two basic types of data in the environment: the environment variables and shell variables
  - `set` will show both the shell and environment variables
  - `printenv` will only show the environment variables
- how is the environment established
  - **a login shell session**:A login shell session is one in which we are prompted for our username and password. This happens when we start a virtual console session, for example.
    - `/etc/profile`: a global configuration script that applies to all users
    - `~/.bash_profile`: a user's personal startup file. this can be used to extend or override settings in the global configuration script.
    - `~/.bash_login`: if `~/.bash_profile` is not found, bash attempts to read this script.
    - `~/.profile`: if neither ~/.bash_profile nor ~/.bash_login is found, bash attempts to read this file. this is the default in Debian-based distributions, such as Ubuntu
  - **a non-login shell session**: A non-login shell session typically occurs when we launch a terminal session in the GUI
     - `/etc/bash.bashrc`: a global configuration script that applies to all users
     - `~/.bashrc`: a user's personal startup file. it can be used to extend or override settings in the global configuration script
  
## A gentle introduction to vi
## customizing the prompt

