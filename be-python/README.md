## be-python

### Quickstart

#### Build the `wheel`

```sh
./build_scripts.sh build
#OR
python setup.py bdist_wheel
```

#### Setup a virtual environment and activate it

```sh
python -m virtualenv .venv
source .venv/bin/activate
```

#### Dev install the project to the virtual environment

```sh
python -m pip install -e .
```

#### Run the app

```sh
python -m py-bookworm
```



```sh
./build_scripts.sh build
python -m virtualenv .venv
source .venv/bin/activate
python -m pip install -e .
python -m py-bookworm
```


