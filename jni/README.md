# Build instructions

## Install Cmake

```shell
winget install Kitware.CMake
```

## Install conan 1.x

```shell
pip install conan<2
```

## Install conan dependencies

```shell
mkdir build
cd build
conan install .. --build=missing
cd ..
```

## Build

Open Clion and build the project via CMake.
