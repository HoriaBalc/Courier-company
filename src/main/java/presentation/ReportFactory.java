package presentation;

public class ReportFactory {
public Report getReport(String rep){
    if(rep.equals("txt")) {
        return new TXTReport();
    }else if(rep.equals("pdf")){
                return new PDFReport();

    }
    return null;



    }

}
