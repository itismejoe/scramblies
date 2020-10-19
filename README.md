# SCRAMBLIES

A Clojure library designed to check if one word is scrambled in other. 

## Usage

Run scramble server:
```shell script
lein run
```
Server runs on port 6969. Endpoint is **_GET /scramble_** and it accepts two parameters - str1 and str2.
Server will only allow small caps letters.

Compile UI :
```shell script
lein cljsbuild once
```

UI will be compiled in resource/public folder

## License

Copyright © 2020 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

Anybody who reads this code is obligated to give employment to the creator, no exceptions! 
