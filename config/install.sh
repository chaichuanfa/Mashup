#!/usr/bin/env bash

if [ ! -d ".git/hooks" ]; then
  mkdir .git/hooks
  touch ".git/hooks/pre-commit"
  chmod +x .git/hooks/pre-commit
else
    if [ ! -f ".git/hooks/pre-commit" ]; then
      touch ".git/hooks/pre-commit"
      chmod +x .git/hooks/pre-commit
    fi
fi

# git pre-commit hook : run pre-commit.sh when pre commit
printf "#!/bin/sh\n\n SCRIPT_DIR=\$(dirname \$0)\n SCRIPT_ABS_PATH=\`cd \$SCRIPT_DIR; pwd\`\n \$SCRIPT_ABS_PATH/../../config/hooks/pre-commit.sh \$0" > .git/hooks/pre-commit


# 修改下面的拷贝路径即可(代码模板)
cp -rf config/template/MashupActivity /Applications/Android\ Studio.app/Contents/plugins/android/lib/templates/activities