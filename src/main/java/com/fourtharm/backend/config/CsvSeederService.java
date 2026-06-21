package com.fourtharm.backend.config;

import com.fourtharm.backend.model.Constituency;
import com.fourtharm.backend.model.County;
import com.fourtharm.backend.model.Ward;
import com.fourtharm.backend.repository.ConstituencyRepository;
import com.fourtharm.backend.repository.CountyRepository;
import com.fourtharm.backend.repository.WardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CsvSeederService
        implements CommandLineRunner {

    private final CountyRepository countyRepository;
    private final ConstituencyRepository constituencyRepository;
    private final WardRepository wardRepository;

    public CsvSeederService(

            CountyRepository countyRepository,

            ConstituencyRepository constituencyRepository,

            WardRepository wardRepository
    ) {

        this.countyRepository =
                countyRepository;

        this.constituencyRepository =
                constituencyRepository;

        this.wardRepository =
                wardRepository;
    }

    @Override
    public void run(String... args)
            throws Exception {

        if (countyRepository.count() > 0) {

            System.out.println(
                    "Governance data already exists."
            );

            return;
        }

        Map<String, County> countyMap =
                new HashMap<>();

        Map<String, Constituency>
                constituencyMap =
                new HashMap<>();

        importCounties(countyMap);

        importConstituencies(
                countyMap,
                constituencyMap
        );

        importWards(
                constituencyMap
        );

        System.out.println(
                "All governance CSV data imported successfully."
        );
    }

    private void importCounties(

            Map<String, County> countyMap

    ) throws Exception {

        BufferedReader reader =
                new BufferedReader(

                        new InputStreamReader(

                                new ClassPathResource(
                                        "data/counties.csv"
                                ).getInputStream()
                        )
                );

        String line;

        reader.readLine();

        while ((line = reader.readLine())
                != null) {

            String countyName =
                    line.trim();

            County county =
                    countyRepository.save(
                            new County(countyName)
                    );

            countyMap.put(
                    countyName,
                    county
            );
        }

        reader.close();
    }

    private void importConstituencies(

            Map<String, County> countyMap,

            Map<String, Constituency>
                    constituencyMap

    ) throws Exception {

        BufferedReader reader =
                new BufferedReader(

                        new InputStreamReader(

                                new ClassPathResource(
                                        "data/constituencies.csv"
                                ).getInputStream()
                        )
                );

        String line;

        reader.readLine();

        while ((line = reader.readLine())
                != null) {

            String[] parts =
                    line.split(",");

            String countyName =
                    parts[0].trim();

            String constituencyName =
                    parts[1].trim();

            County county =
                    countyMap.get(countyName);

            if (county == null) {

                System.out.println(
                        "Missing county: "
                                + countyName
                );

                continue;
            }

            Constituency constituency =
                    constituencyRepository.save(

                            new Constituency(
                                    constituencyName,
                                    county
                            )
                    );

            constituencyMap.put(
                    constituencyName,
                    constituency
            );
        }

        reader.close();
    }

    private void importWards(

            Map<String, Constituency>
                    constituencyMap

    ) throws Exception {

        BufferedReader reader =
                new BufferedReader(

                        new InputStreamReader(

                                new ClassPathResource(
                                        "data/wards.csv"
                                ).getInputStream()
                        )
                );

        String line;

        reader.readLine();

        while ((line = reader.readLine())
                != null) {

            String[] parts =
                    line.split(",");

            String constituencyName =
                    parts[0].trim();

            String wardName =
                    parts[1].trim();

            Constituency constituency =
                    constituencyMap.get(
                            constituencyName
                    );

            if (constituency == null) {

    System.out.println(
            "FAILED WARD IMPORT -> Constituency not found: "
                    + constituencyName
                    + " | Ward: "
                    + wardName
    );

    continue;
}

            wardRepository.save(

                    new Ward(
                            wardName,
                            constituency
                    )
            );
        }

        reader.close();
    }
}