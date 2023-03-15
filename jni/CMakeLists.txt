cmake_minimum_required(VERSION 3.24)

include(CMakePrintHelpers)

project(TdJNI)

include("${CMAKE_BINARY_DIR}/conanbuildinfo.cmake")
conan_basic_setup(TARGET)

if (NOT JNI_FOUND)
    find_package(JNI REQUIRED)
endif ()
cmake_print_variables(JNI_INCLUDE_DIRS)
cmake_print_variables(JNI_LIBRARIES)

get_filename_component(TD_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../tdlib" ABSOLUTE)
cmake_print_variables(TD_DIR)
option(TD_ENABLE_JNI "Enable JNI-compatible TDLib API" ON)
option(TD_ENABLE_MULTI_PROCESSOR_COMPILATION "Enable multi-processor compilation." ON)
add_subdirectory("${TD_DIR}" tdlib)

add_library(
        TdJNI SHARED
        td_jni.cpp
)
target_link_libraries(TdJNI PRIVATE JNI::JNI)
target_link_libraries(TdJNI PRIVATE Td::TdStatic)

get_filename_component(TD_JNI_INSTALL_PREFIX "${CMAKE_CURRENT_SOURCE_DIR}/out" ABSOLUTE)
set(CMAKE_INSTALL_PREFIX "${TD_JNI_INSTALL_PREFIX}")
install(TARGETS TdJNI)
