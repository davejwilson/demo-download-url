# Get Nexus Download URL
This is a demo of how to get the Nexus download URL for the current version of the artifact.

# Approach
It uses the maven groovy plugin, instead of writing a new mojo.
The `project.artifact.version` property is used to search for the asset.
This can be changed to use the base version instead, but would produce mutiple matches for a snapshot version.

# Usage
The idea would be to use this type of example to either download or save the download url of a newly built artifact version.
To satisfy the version number, another repo with a version example can be used.
The download url of the version of the artifact will be found in the `project.url` property.
