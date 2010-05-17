package escom.hoc;

import java.util.List;

public class Utils {
	public static List addAll(List original, List other) {
		List<String> d = (List<String>)other;
		String s;
		String r;
		for(int i = 0; i < d.size(); i++) {
			s = d.get(i);
			if("whilecode".equals(s)) {
				for(int j = 1; j < 3; j++) {
					r = String.valueOf(
						Integer.parseInt(
							d.get(i + j)
				       	 	) + original.size()
				    	);
					d.set(i + j, r);
				}
				continue;
			}
			if("ifcode".equals(s)) {
				for(int j = 1; j < 4; j++) {
					if("stop".equals(d.get(i + j))) {
						continue;
					}
					r = String.valueOf(
						Integer.parseInt(
							d.get(i + j)
				       	 	) + original.size()
				    	);
					d.set(i + j, r);
				}
			}
			if("forcode".equals(s)) {
				for(int j = 1; j < 5; j++) {
					if("stop".equals(d.get(i + j))) {
						continue;
					}
					r = String.valueOf(
						Integer.parseInt(
							d.get(i + j)
				       	 	) + original.size()
				    	);
					d.set(i + j, r);
				}
			}
            if("switchcode".equals(s) || "casecode".equals(s)) {
				for(int j = 1; j < 3; j++) {
					if("stop".equals(d.get(i + j))) {
						continue;
					}
					r = String.valueOf(
						Integer.parseInt(
							d.get(i + j)
				       	 	) + original.size()
				    	);
					d.set(i + j, r);
				}
			}
		}
		original.addAll(other);
		return original;
	}


}

