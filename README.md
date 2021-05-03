## Communication template filler

----------------
### Demonstration
Fill letter template<br />
![ezgif com-gif-maker](https://user-images.githubusercontent.com/82434097/116941224-21c08f80-ac24-11eb-95e6-ceb52e814aac.gif)
<br />

Fill email template<br />
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/82434097/116941279-3ef55e00-ac24-11eb-8849-5d807674842b.gif)
<br />

### Overview
This program extracts the contact information stored in a CSV file and fills the template file placeholders with appropriate fields. The templates are stored as text files with special placeholders in the text that refer to the CSV file’s header names. Placeholders are CSV column headers between [[ and ]] e.g. [[first_name]]. This program is run on a command line, taking a CSV file, template, and output path as an input. The generated files that will contain the messages will be saved in the specified output path.

### Commands
The program will accept the following commands in any order.<br />
* ```--email```: Generate email messages. If this option is provided, then ```--email-template <path/to/file>``` must also be provided.
* ```--email-template <path/to/file>```: Indicates a file name for email template.
* ```--letter```: Generate letters. If this option  is provided, then ```--letter-template <path/to/file>``` must also be provided.
* ```--letter-template <path/to/file>```: Indicates a file name for letter template.
* ```--output-dir <path/to/folder>```: Indicates a folder to store all generated files. This option is required.
* ```--csv-file <path/to/file>```: Indicates CSV file to process. This option is required.