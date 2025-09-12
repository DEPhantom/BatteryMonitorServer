
from rich.progress import Progress, BarColumn, TextColumn, TimeRemainingColumn, TaskProgressColumn, Column, ProgressColumn

from rich.text import Text
import time

# 自訂 Epoch 欄位
class EpochColumn(ProgressColumn):
    def render(self, task):
        """渲染自訂的 epoch 欄位"""
        epoch = task.fields.get("epoch", "N/A")
        return Text(f"Epoch: {epoch}", style="bold cyan")
    
    def get_table_column(self):
        """定義表格欄位屬性"""
        return Column(min_width=12)

# 建立自訂的 Progress
progress = Progress(
    # 描述欄位
    TextColumn("[bold blue]深度學習模型訓練進度"),
    
    # 自訂的 Epoch 欄位
    EpochColumn(),
    
    # 進度條 - 寬度40，各種顏色設定
    BarColumn(
        bar_width=40,
        style="bright_green",           # 已完成部分 - 亮綠色
        complete_style="bold green",    # 完全完成時 - 粗體綠色
        finished_style="bold blue",     # 結束時 - 粗體藍色
        pulse_style="bold yellow"       # 脈衝動畫 - 粗體黃色
    ),
    
    # 自訂百分比格式和顏色
    TextColumn("[progress.percentage][bold magenta]{task.percentage:>3.1f}%"),
    
    # 已完成/總數 - 設定顏色
    TaskProgressColumn(
        text_format="[bold white]{task.completed}/{task.total}",
        style="bold white"
    ),
    
    # 剩餘時間 - 設定顏色
    TimeRemainingColumn(
        compact=True,
        elapsed_when_finished=True
    ),
    
    # 其他設定
    auto_refresh=True,
    refresh_per_second=10,
)

# 使用範例
def demo_training():
    with progress:
        # 創建任務，total=100，並設定自訂 epoch 欄位
        task = progress.add_task(
            description="深度學習模型訓練進度",
            total=100,
            epoch=1  # 自訂欄位
        )
        
        # 模擬訓練過程
        for i in range(100):
            time.sleep(0.1)  # 模擬處理時間
            
            # 每10步更新一次 epoch
            if i % 10 == 0 and i > 0:
                current_epoch = (i // 10) + 1
                progress.update(task, epoch=current_epoch)
            
            # 推進進度
            progress.advance(task, 1)

# 執行演示
if __name__ == "__main__":
    print("開始自訂進度條演示...")
    demo_training()
    print("完成！")