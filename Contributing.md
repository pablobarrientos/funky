# Developer Guide

This document describes the organization and coding conventions used in the library. It is intended for contributors,
but may also be of interest to users.

### Module Structure

`org.funky` is the core package. Any new construct of feature should be created as a child of that package, unless modifying existing packages/classes.

### Type Classes

#### Methods

Each new method needs to be properly documented, including return type. Giving usage examples would be ideal though it is not mandatory.
Use standard naming conventions. Klint will keep the code tidy upon build.

Add general purpose derived methods directly to the type class. These may be left unfinalized, to allow a
child type class or a type class instance to override them for efficiency.

All function extensions involving a type should be defined in the file where the file is defined.

### Data Structures

Define types representing a data structure as a sealed class. Define type class instances in the same files as the data structure.

### General

#### Type annotations

 * Annotate the return type of all public methods except when overriding methods (non-mandatory)
 * Annotate any exceptions that the method could throw

### How can you contribute?

 * Write test cases
 * Add any missing/new data structure
 * Documentation
    * Improve class level and method documentation for each type class and file.
 * Review code base for consistency problems