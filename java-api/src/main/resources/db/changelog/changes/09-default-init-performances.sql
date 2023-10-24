--liquibase formatted sql
--changeset IvanZhuravlev:09-default-init-performances
INSERT INTO performances(name, description, budget) VALUES
                                                        ('Shakespearean Play','Because Shakespeares plays are written to be acted, they are constantly fresh and can be adapted to the place and time they are performed. Their language is wonderfully expressive and powerful, and although it may sometimes seem hard to understand in reading, actors can bring it to vivid life for us.',5000.00),
                                                        ('Rock Concert','A rock concert is a musical performance by a rock band or solo artist. It typically takes place in front of an audience of cheering fans who are eager to see their favorite performers in action. The music at a rock concert is loud and energetic, and the atmosphere is often one of excitement and anticipation.',7500.50),
                                                        ('Dance Performance','The singers performance will be of a charitable nature. Part of the funds from the profit will be transferred to the needs of the Armed Forces.On December 1, the main hitmaker of the country will return to the stage of the Palace of Sports to present the completely Ukrainian-language show "Zorepad".
For the first time, hits from the singers new record will be heard from the stage, which took first place in Apple Music immediately after its release, and several main singles were picked up by Ukrainian radio stations at the same time. Maks Barskikh: "The main goal for me was to create the most successful album in my career, which should be in the Ukrainian language. ',3000.75),
                                                        ('Comedy Show','Stand-up comedy is a comedic performance to a live audience in which the performer addresses the audience directly from the stage. The performer is known as a comedian, comic, or stand-up. It is usually a rhetorical performance but many comics employ crowd interaction as part of their set or routine. ',4000.25),
                                                        ('Orchestra Concert','Other instruments such as the piano, harpsichord, and celesta may sometimes appear in a fifth keyboard section or may stand alone as soloist instruments, as may the concert harp and, for performances of some modern compositions, electronic instruments, and guitars.

A full-size Western orchestra may sometimes be called a symphony orchestra or philharmonic orchestra. The number of musicians employed in a given performance may vary from seventy to over one hundred, depending on the work being played and the venue size. A chamber orchestra (sometimes a concert orchestra) is a smaller ensemble of not more than about fifty musicians',6000.00);