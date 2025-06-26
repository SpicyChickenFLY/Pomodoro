#!/bin/bash

# 番茄钟工具
# 使用方法: ./pomodoro.sh [工作时间(分钟)] [休息时间(分钟)]

# 设置默认值
WORK_TIME=${1:-25} # 工作时间，默认为25分钟
REST_TIME=${2:-5}  # 休息时间，默认为5分钟
CYCLES=0           # 完成的番茄钟循环计数
TOTAL_CYCLES=0     # 总循环计数
MODE="work"        # 当前模式：work或rest

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 显示帮助信息
function show_help {
	echo -e "${YELLOW}番茄钟工具使用方法:${NC}"
	echo -e "  $0 [工作时间(分钟)] [休息时间(分钟)]"
	echo -e "示例:"
	echo -e "  $0         # 使用默认值(工作25分钟，休息5分钟)"
	echo -e "  $0 30 10   # 工作30分钟，休息10分钟"
	echo -e "\n${YELLOW}快捷键:${NC}"
	echo -e "  p - 暂停/继续"
	echo -e "  s - 跳过当前阶段"
	echo -e "  r - 重置计时器"
	echo -e "  q - 退出"
}

# 显示状态
function show_status {
	clear
	echo -e "${YELLOW}番茄钟计时器${NC}"
	echo -e "---------------------"

	if [ "$MODE" == "work" ]; then
		echo -e "模式: ${RED}工作${NC} (${WORK_TIME}分钟)"
		echo -e "已完成循环: ${CYCLES}"
	else
		echo -e "模式: ${GREEN}休息${NC} (${REST_TIME}分钟)"
	fi

	echo -e "总循环计数: ${TOTAL_CYCLES}"
	echo -e "---------------------"
	echo -e "剩余时间: ${BLUE}$1${NC}"
	echo -e "---------------------"
	echo -e "快捷键: p-暂停, s-跳过, r-重置, q-退出"
}

# 转换秒为分钟:秒格式
function format_time {
	local total_seconds=$1
	local minutes=$((total_seconds / 60))
	local seconds=$((total_seconds % 60))
	printf "%02d:%02d" $minutes $seconds
}

# 倒计时函数
function countdown {
	local seconds_left=$1
	local paused=false
	local remaining_time

	while [ $seconds_left -gt 0 ]; do
		# 检查用户输入
		if read -r -s -n 1 -t 1 key; then
			case $key in
			p)
				if $paused; then
					paused=false
					echo -e "\n${GREEN}继续...${NC}"
				else
					paused=true
					echo -e "\n${YELLOW}已暂停${NC}"
				fi
				;;
			s)
				echo -e "\n${BLUE}跳过当前阶段${NC}"
				seconds_left=0
				;;
			r)
				echo -e "\n${YELLOW}重置计时器${NC}"
				return 1
				;;
			q)
				echo -e "\n${RED}退出番茄钟${NC}"
				exit 0
				;;
			*)
				echo -e "\n${RED}未知命令${NC}"
				show_help
				;;
			esac
		fi

		if ! $paused; then
			remaining_time=$(format_time $seconds_left)
			show_status "$remaining_time"
			seconds_left=$((seconds_left - 1))
		fi
	done

	return 0
}

# 主循环
while true; do
	if [ "$MODE" == "work" ]; then
		show_status "$(format_time $((WORK_TIME * 60)))"
		echo -e "\n${RED}开始工作!${NC}"
		if ! countdown $((WORK_TIME * 60)); then
			MODE="work"
			continue
		fi
		MODE="rest"
		CYCLES=$((CYCLES + 1))
		TOTAL_CYCLES=$((TOTAL_CYCLES + 1))
		echo -e "\n${GREEN}工作时间结束! 开始休息.${NC}"
		# 播放提示音（如果系统支持）
		echo -e "\a" >/dev/tty
	else
		show_status "$(format_time $((REST_TIME * 60)))"
		echo -e "\n${GREEN}开始休息!${NC}"
		if ! countdown $((REST_TIME * 60)); then
			MODE="work"
			continue
		fi
		MODE="work"
		echo -e "\n${RED}休息时间结束! 开始工作.${NC}"
		# 播放提示音（如果系统支持）
		echo -e "\a" >/dev/tty
	fi
done
