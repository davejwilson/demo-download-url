# Get Nexus Download URL
This is a demo of how to get the Nexus download URL for the current version of the artifact.
It has been combined with git versioning and build registration examples.

# Approach
It uses the maven groovy plugin, instead of writing a new mojo.

# Usage
The `session.UserProperties` are used to keep information between plugin executions.
This demo is driven through 3 profiles: `git-versioning`, `nexus-download-url` and `build-registration` 
which has 2 goal executions `validate-build` and `register-build`.