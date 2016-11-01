import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class myFileEditor
{
	public static void main(String[] args)
	{
		String file = "D:\\Java\\SDKFileEditor\\src\\MyData.txt";
		StringBuilder sbfContent = new StringBuilder();
		String logFile = "D:\\Java\\SDKFileEditor\\src\\MyLog.txt";
		StringBuilder sbLog = new StringBuilder();

		// Reading
		try
		{
			InputStream ips = new FileInputStream(file);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			int i = 1;

			while ((line = br.readLine()) != null)
			{
				int lineLenght = line.length();
				String _line = line.trim();
				int lineLenghtTrimed = _line.length();

				// Output in console window
				System.out.println(_line);
				sbfContent.append(_line + "\r\n");

				// Output LOG: Content + Comment Count removed Space
				sbLog.append(String.format("%1$05d;%2$s ", i, _line));

				int lineDiff = lineLenght - lineLenghtTrimed;
				if (lineDiff > 0)
				{
					sbLog.append(String.format("### space removed ( %1s )", lineDiff));
				}

				sbLog.append("\r\n");
				i++;
			}
			br.close();
			sbLog.append("\n Input file processed at " + getCurrentDateTime());
		} catch (Exception e)
		{
			System.out.println(e.toString());
			sbLog.append(e.toString());
		}

		// Output readed File
		sbLog.append(writeMyFile(file, sbfContent.toString().trim()));
		// Output logFile
		System.out.println(writeMyFile(logFile, sbLog.toString()));

	}

	private static String getCurrentDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime()); // System.out.println(dateFormat.format(cal.getTime()));
	}

	private static String writeMyFile(String fName, String fContent)
	{
		String ret = "";
		try
		{
			FileWriter fw = new FileWriter(fName);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter fileOut = new PrintWriter(bw);

			fileOut.println(fContent);
			// fileOut.println("\n Datei wurde überarbeitet am ");
			fileOut.close();
			// System.out.println("the file " + file + " is created!");
		} catch (Exception e)
		{
			ret = e.toString(); // System.out.println(e.toString());
		}
		return ret;
	}

}
