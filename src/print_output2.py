import sys

def main( device_name, battery_Pct ):
    
    print("success")
    print("-------------------------------------------------------------------------")
        
# end main()

if __name__ == "__main__":
    
    print("hello")
    
    if len(sys.argv) == 3:
        device_name = sys.argv[1]
        battery_Pct = sys.argv[2]
    # end if
    else :
        print("error")
    # end else

    main( device_name, battery_Pct )
# end if