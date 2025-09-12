from rich.progress import Progress, BarColumn, TextColumn, TimeRemainingColumn, TaskProgressColumn, Column, ProgressColumn
from rich import print
from rich.rule import Rule
import sys

progress = Progress(
    
    TextColumn("[indian_red1]深度學習模型訓練進度work"),
    
    BarColumn(
        bar_width=40,
        style="bright_black",           
        complete_style="bold yellow",    
        finished_style="orange_red1",     
        pulse_style="yellow"       # 脈衝動畫 - 粗體黃色
    ),
   
    
    TextColumn("[progress.percentage][bright_red]{task.percentage:>3.1f}%"),
        
    TaskProgressColumn(
        text_format="{task.completed}/{task.total}",
        style="purple4"
    ),    
    
    TimeRemainingColumn(
        compact=True,
        elapsed_when_finished=True
    ),
    
    auto_refresh=True,
    refresh_per_second=10,
)

def progress_init( device_name, battery_Pct ):
    global progress
    title = "[indian_red1]"+device_name
    battery_Color = "bold yellow"
    
    progress = Progress(
    
        TextColumn(title),
    
        BarColumn(
            bar_width=40,
            style="bright_black",           
            complete_style=battery_Color,    
            finished_style="orange_red1",     
            pulse_style="yellow"       # 脈衝動畫 - 粗體黃色
        ),
   
    
        TextColumn("[progress.percentage][bright_red]{task.percentage:>3.1f}%"),
        
        TaskProgressColumn(
            text_format="{task.completed}/{task.total}",
            style="purple4"
        ),    
    
        TimeRemainingColumn(
            compact=True,
            elapsed_when_finished=True
        ),
    
        auto_refresh=True,
        refresh_per_second=10,
    )
    
    
    
    
# end progress_init()

def main( device_name, battery_Pct ):
    
    print("[blink italic purple4]" + device_name + "[/blink italic purple4]")
    
    progress_init( device_name, battery_Pct )
    
    with progress:
        task = progress.add_task(
            description="title",
            total=100  
        )
        
        progress.update(task, advance=int(battery_Pct))
        
    # end with
    
    print("")
    print(Rule(style="green"))
        
# end main()

if __name__ == "__main__":
    
    if len(sys.argv) == 3:
        device_name = sys.argv[1]
        battery_Pct = sys.argv[2]
    # end if
    else :
        print("error")
    # end else

    main( device_name, battery_Pct )
# end if