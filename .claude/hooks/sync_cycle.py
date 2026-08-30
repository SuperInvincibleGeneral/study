#!/usr/bin/env python3
"""Cycle 同期フック（SessionStart 用）

~/Cycle または ../Cycle が存在すれば git pull で最新化し、
どちらも無ければ GitHub から ~/Cycle に clone する。
Windows / Linux（Webコンテナ）共通で動作する。
失敗してもセッションは止めない（Cycle 未到達として CLAUDE.md 側の宣言ルールに委ねる）。
"""
import subprocess
import sys
from pathlib import Path

CYCLE_URL = "https://github.com/SuperInvincibleGeneral/Cycle.git"


def repo_root() -> Path:
    # このファイルは <repo>/.claude/hooks/sync_cycle.py に置かれる想定
    return Path(__file__).resolve().parents[2]


def main() -> int:
    candidates = [Path.home() / "Cycle", repo_root().parent / "Cycle"]

    for path in candidates:
        if (path / ".git").exists():
            result = subprocess.run(
                ["git", "-C", str(path), "pull", "--ff-only"],
                capture_output=True, text=True,
            )
            status = "更新完了" if result.returncode == 0 else f"pull 失敗（既存の写しで続行）: {result.stderr.strip()}"
            print(f"[sync_cycle] {path}: {status}")
            return 0

    dest = Path.home() / "Cycle"
    result = subprocess.run(
        ["git", "clone", "--depth", "1", CYCLE_URL, str(dest)],
        capture_output=True, text=True,
    )
    if result.returncode == 0:
        print(f"[sync_cycle] {dest}: clone 完了")
    else:
        print(f"[sync_cycle] Cycle 未到達（clone 失敗）: {result.stderr.strip()}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
