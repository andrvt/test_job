package ru.test;


import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import ru.test.dto.Ticket;
import ru.test.util.FileUtil;
import static ru.test.util.Message.msg;



public class Start {

	public static void main(String[] args)  {
		final String destinationFrom = "Владивосток";
		final String destinationTo = "Тель-Авив";
		final String fileName = "tickets.json";
		final String destinationFromTmz= "Asia/Vladivostok";
		final String destinationToTmz= "Asia/Jerusalem";
		String dir = System.getProperty("user.dir");
		String filePath = dir+File.separator+fileName;
        msg("Ожидаемый файл для обработки = " + filePath);
        File fileJson = new File(filePath);
        //Проверка файла
        if (!fileJson.exists() || fileJson.isDirectory()){
            msg("Файл не существует!");
            help_msg();
            return ;
        }
		List<Ticket> tickets = FileUtil.readJson(fileJson,destinationFromTmz,destinationToTmz);
		if (tickets.size()==0){
			bad_structure();
			return;
		}
		msg("==================================");
		msg("Ищем рейсы между городами: \n\t"+destinationFrom+" и "+destinationTo);
		List<Ticket> destinationListTo = tickets.stream()
				.filter(t ->t.getOrigin_name().equals(destinationFrom) && t.getDestination_name().equals(destinationTo) )
				.collect(Collectors.toList());
		
		List<Ticket> destinationListFrom = tickets.stream()
				.filter(t ->t.getOrigin_name().equals(destinationTo) && t.getDestination_name().equals(destinationFrom) )
				.collect(Collectors.toList());
		msg("==================================");
		msg("Найдено рейсов из : "+destinationFrom+"\tдо : "+destinationTo + "\t : "+destinationListTo.size());
		msg("Найдено рейсов из : "+destinationTo+"\tдо : "+destinationFrom + "\t : "+destinationListFrom.size());
		msg("==================================");
		if (destinationListTo.size()>0){
			calculate(destinationFrom, destinationTo, destinationListTo);
		}
		if (destinationListFrom.size()>0){
			calculate(destinationTo, destinationFrom, destinationListFrom);
		}

	}
	private static void calculate(String destFrom, String destTo, List<Ticket> list){
		msg("Рейсы из : "+destFrom+"\tдо : "+destTo + "\t : "+list.size());
		Map<String,List<Ticket>> ticket_group = list.stream()
														.collect(Collectors.groupingBy(Ticket::getCarrier));
		for(Entry<String, List<Ticket>> item : ticket_group.entrySet()){
			msg("----------------------------------------------------------");
			msg("*********   Авиакомпания   "+item.getKey()+"  ********* ");
			List<Ticket> ticks = item.getValue();
			msg("           Найдено рейсов : "+ticks.size());
			long time=  ticks.stream().min(Comparator.comparing(Ticket::getDuration)).get().getDuration();
			msg("Минимальное время полета  : "+time/60 +" часов " +time%60+" минут" +" ("+time+" минут)" );		
		}
		double avg = list.stream().collect(Collectors.averagingInt(Ticket::getPrice)).doubleValue(); 
		DoubleStream sortedPrice = list.stream().mapToDouble(Ticket::getPrice).sorted();
		double mediana = list.size()%2==0?
				sortedPrice.skip(list.size()/2-1).limit(2).average().getAsDouble():
				sortedPrice.skip(list.size()/2).findFirst().getAsDouble();	
	    msg("----------------------------------------------------------");
	    msg("    Средняя цена билета   : "+String.format("%.2f",avg) );
		msg("    Медиана цена билета   : "+String.format("%.2f",mediana) );
		msg("Разница между средней и медианой : "+String.format("%.2f",Math.abs(mediana-avg)) );
		
	}
	private static void help_msg() {
		StringBuilder sb = new StringBuilder();
		sb.append("!==========\n");
		sb.append("Для работы программы необходимо\n");
		sb.append("	1.Java установленная в системе,должна быть не меньше версии 8 ! \n");
		sb.append("	2.поместить файл 'tickets.json' в один каталог с программой ! \n");
		sb.append("==========!\n");
		msg(sb.toString());
	}
	private static void bad_structure() {
		StringBuilder sb = new StringBuilder();
		sb.append("!==========\n");
		sb.append("Структура файла не опрделена! \n");
		sb.append("==========!\n");
		msg(sb.toString());
	}
}
