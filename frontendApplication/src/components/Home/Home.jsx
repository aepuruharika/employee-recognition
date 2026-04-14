export default function Home() {
  return (
    <div className="min-h-screen relative">

      {/* HERO IMAGE SECTION */}
      <div className="relative h-[85vh] flex items-center justify-center">

        {/* BACKGROUND IMAGE */}
        <div
          className="absolute inset-0 bg-cover bg-center"
          style={{
            backgroundImage:
              "url('https://images.unsplash.com/photo-1522071820081-009f0129c71c')",
          }}
        />

        {/* DARK OVERLAY */}
        <div className="absolute inset-0 bg-black/60" />

        {/* CONTENT ON IMAGE */}
        <div className="relative z-10 text-center px-6 max-w-4xl h-full w-full flex flex-col justify-start pt-10">

          {/* TITLE (NOW AT TOP OF IMAGE) */}
          <h1 className="text-2xl md:text-3xl font-semibold text-gray-200 tracking-wide">
            Employee Recognition System
          </h1>

          {/* CAPTION (CENTERED + BIG) */}
          <div className="flex flex-1 items-center justify-center">
            <p className="text-2xl md:text-4xl font-bold text-white leading-snug">
              Where effort is seen, achievements are valued, and growth is celebrated
            </p>
          </div>

        </div>

      </div>

    </div>
  );
}